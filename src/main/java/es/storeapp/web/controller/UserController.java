package es.storeapp.web.controller;

import es.storeapp.business.entities.User;
import es.storeapp.business.exceptions.AuthenticationException;
import es.storeapp.business.exceptions.DuplicatedResourceException;
import es.storeapp.business.exceptions.InstanceNotFoundException;
import es.storeapp.business.exceptions.ServiceException;
import es.storeapp.business.services.UserService;
import es.storeapp.common.Constants;
import es.storeapp.web.cookies.UserInfo;
import es.storeapp.web.exceptions.ErrorHandlingUtils;
import es.storeapp.web.forms.LoginForm;
import es.storeapp.web.forms.ChangePasswordForm;
import es.storeapp.web.forms.ResetPasswordForm;
import es.storeapp.web.forms.UserProfileForm;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import es.storeapp.common.EscapingLoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private static final EscapingLoggerWrapper logger = new EscapingLoggerWrapper(UserController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;
    
    @Autowired
    ErrorHandlingUtils errorHandlingUtils;
    
    @GetMapping(Constants.LOGIN_ENDPOINT)
    public String doGetLoginPage(Model model) {
        model.addAttribute(Constants.LOGIN_FORM, new LoginForm());
        return Constants.LOGIN_PAGE;
    }

    @GetMapping(Constants.LOGOUT_ENDPOINT)
    public String doLogout(HttpSession session, 
                           HttpServletResponse response,
                           @CookieValue(value = Constants.PERSISTENT_USER_COOKIE, required = false) String userInfo) {
        if (userInfo != null) {
            Cookie userCookie = new Cookie(Constants.PERSISTENT_USER_COOKIE, null);
            userCookie.setHttpOnly(true);
            userCookie.setSecure(true);
            userCookie.setMaxAge(0); // remove
            response.addCookie(userCookie);
        }
        if (session != null) {
            session.invalidate();
        }
        return Constants.SEND_REDIRECT + Constants.ROOT_ENDPOINT;
    }

    @GetMapping(Constants.REGISTRATION_ENDPOINT)
    public String doGetRegisterPage(Model model) {
        model.addAttribute(Constants.USER_PROFILE_FORM, new UserProfileForm());
        return Constants.USER_PROFILE_PAGE;
    }

    @GetMapping(Constants.USER_PROFILE_ENDPOINT)
    public String doGetProfilePage(@SessionAttribute(Constants.USER_SESSION) User user,
                                   Model model) {
        UserProfileForm form = new UserProfileForm(user.getName(),
                user.getEmail(), user.getAddress());
        model.addAttribute(Constants.USER_PROFILE_FORM, form);
        return Constants.USER_PROFILE_PAGE;
    }

    @GetMapping(Constants.CHANGE_PASSWORD_ENDPOINT)
    public String doGetChangePasswordPage(Model model, @SessionAttribute(Constants.USER_SESSION) User user) {
        ChangePasswordForm form = new ChangePasswordForm();
        model.addAttribute(Constants.PASSWORD_FORM, form);
        return Constants.PASSWORD_PAGE;
    }

    @GetMapping(Constants.SEND_EMAIL_ENDPOINT)
    public String doGetSendEmailPage(Model model) {
        return Constants.SEND_EMAIL_PAGE;
    }

    @GetMapping(Constants.RESET_PASSWORD_ENDPOINT)
    public String doGetResetPasswordPage(@RequestParam(value = Constants.TOKEN_PARAM) String token,
                                         @RequestParam(value = Constants.EMAIL_PARAM) String email,
                                         Model model) {
        ResetPasswordForm form = new ResetPasswordForm();
        form.setEmail(email);
        form.setToken(token);
        model.addAttribute(Constants.RESET_PASSWORD_FORM, form);
        return Constants.RESET_PASSWORD_PAGE;
    }
    
    @PostMapping(Constants.LOGIN_ENDPOINT)
    public String doLogin(@Valid @ModelAttribute LoginForm loginForm, 
                          BindingResult result,
                          HttpSession session,
                          HttpServletResponse response,
                          Locale locale, 
                          Model model) throws JAXBException {
        if (result.hasErrors()) {  
            errorHandlingUtils.handleInvalidFormError(result, 
                Constants.REGISTRATION_INVALID_PARAMS_MESSAGE, model, locale);
            return Constants.USER_PROFILE_PAGE;
        }
        User user;
        try {
            user = userService.login(loginForm.getEmail(), loginForm.getPassword());
            session.setAttribute(Constants.USER_SESSION, user);
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("User {0} logged in", user.getEmail()));
            }
            if (loginForm.getRememberMe() != null && loginForm.getRememberMe()) {
                Base64.Encoder encoder = Base64.getEncoder();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                UserInfo userInfo = new UserInfo(user.getEmail(), user.getPassword());
                JAXBContext context = JAXBContext.newInstance(UserInfo.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
                marshaller.marshal(userInfo, buffer);
                Cookie userCookie = new Cookie(Constants.PERSISTENT_USER_COOKIE,
                        new String(encoder.encode(buffer.toByteArray())));
                userCookie.setMaxAge(604800); // 1 week
                userCookie.setHttpOnly(true);
                userCookie.setSecure(true);
                response.addCookie(userCookie);
            }
        } catch (AuthenticationException ex) {
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("User {0} not logged in ", loginForm.getEmail()));
            }
            return errorHandlingUtils.handleAuthenticationException(ex, loginForm.getEmail(), 
                    Constants.LOGIN_PAGE, model, locale);
        }
        return Constants.SEND_REDIRECT + Constants.ROOT_ENDPOINT;
    }

    @PostMapping(Constants.REGISTRATION_ENDPOINT)
    public String doRegister(@Valid @ModelAttribute(Constants.USER_PROFILE_FORM) UserProfileForm userProfileForm,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             HttpSession session,
                             Locale locale, 
                             Model model) {
        if (result.hasErrors()) {
            errorHandlingUtils.handleInvalidFormError(result, 
                Constants.REGISTRATION_INVALID_PARAMS_MESSAGE, model, locale);
            return Constants.USER_PROFILE_PAGE;
        }
        User user;
        try {
            if(userProfileForm.getImage() == null){
                user = userService.create(userProfileForm.getName(), userProfileForm.getEmail(),
                        userProfileForm.getPassword(), userProfileForm.getAddress(), null, null);
            }else{
                MultipartFile file = userProfileForm.getImage();
                String contentType = file.getContentType();
                if(contentType != null && !contentType.contains("jpeg")){
                    user = userService.create(userProfileForm.getName(), userProfileForm.getEmail(),
                            userProfileForm.getPassword(), userProfileForm.getAddress(), null, null);
                }else{
                    user = userService.create(userProfileForm.getName(), userProfileForm.getEmail(),
                            userProfileForm.getPassword(), userProfileForm.getAddress(), userProfileForm.getImage().getOriginalFilename(),
                            userProfileForm.getImage().getBytes());
                }
            }
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("User {0} with name {1} registered", user.getEmail(), user.getName()));
            }
            session.setAttribute(Constants.USER_SESSION, user);
            redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, messageSource.getMessage(
                    Constants.REGISTRATION_SUCCESS_MESSAGE, new Object[]{user.getName()}, locale));
        } catch (DuplicatedResourceException ex) {
            return errorHandlingUtils.handleDuplicatedResourceException(ex, Constants.USER_PROFILE_PAGE,
                    model, locale);
        } catch (IOException ex) {
            return errorHandlingUtils.handleUnexpectedException(ex, model);
        }
        return Constants.SEND_REDIRECT + Constants.ROOT_ENDPOINT;
    }

    @PostMapping(Constants.USER_PROFILE_ENDPOINT)
    public String doUpdateProfile(@Valid @ModelAttribute(Constants.USER_PROFILE_FORM) UserProfileForm userProfileForm,
                                  BindingResult result,
                                  @SessionAttribute(Constants.USER_SESSION) User user,            
                                  HttpSession session,
                                  Locale locale, 
                                  Model model) {
        if (result.hasErrors()) {
            errorHandlingUtils.handleInvalidFormError(result, 
                Constants.UPDATE_PROFILE_INVALID_PARAMS_MESSAGE, model, locale);
            return Constants.USER_PROFILE_PAGE;
        }
        User updatedUser;
        try {
            if(userProfileForm.getImage() == null){
                updatedUser = userService.update(user.getUserId(), userProfileForm.getName(), userProfileForm.getEmail(),
                    userProfileForm.getAddress(), null, null);
            }else{
                MultipartFile file = userProfileForm.getImage();
                String contentType = file.getContentType();
                if(contentType != null && !contentType.contains("jpeg")){
                    updatedUser = userService.update(user.getUserId(), userProfileForm.getName(), userProfileForm.getEmail(),
                        userProfileForm.getAddress(), null, null);
                }else{
                    updatedUser = userService.update(user.getUserId(), userProfileForm.getName(), userProfileForm.getEmail(),
                    userProfileForm.getAddress(), userProfileForm.getImage().getOriginalFilename(), 
                    userProfileForm.getImage().getBytes());
                }
            }
            if(logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("User {0} with name {1} updated", 
                        updatedUser.getEmail(), updatedUser.getName()));
            }
            
            session.setAttribute(Constants.USER_SESSION, updatedUser);
            model.addAttribute(Constants.SUCCESS_MESSAGE, messageSource.getMessage(
                Constants.PROFILE_UPDATE_SUCCESS, new Object[]{}, locale));
            
        } catch (InstanceNotFoundException ex) {
            return errorHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        } catch (DuplicatedResourceException ex) {
            return errorHandlingUtils.handleDuplicatedResourceException(ex, Constants.ERROR_PAGE, model, locale);
        } catch (Exception ex) {
            return errorHandlingUtils.handleUnexpectedException(ex, model);
        }
        return Constants.USER_PROFILE_PAGE;
    }

    @PostMapping(Constants.CHANGE_PASSWORD_ENDPOINT)
    public String doChangePassword(@Valid @ModelAttribute(Constants.PASSWORD_FORM) ChangePasswordForm passwordForm,
                                   BindingResult result,
                                   @SessionAttribute(Constants.USER_SESSION) User user,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes,
                                   Locale locale, 
                                   Model model) {
        if (result.hasErrors()) {       
            errorHandlingUtils.handleInvalidFormError(result, 
                Constants.CHANGE_PASSWORD_INVALID_PARAMS_MESSAGE, model, locale);            
            return Constants.PASSWORD_PAGE;
        }
        User updatedUser;
        try {
            updatedUser = userService.changePassword(user.getUserId(), passwordForm.getOldPassword(), passwordForm.getPassword());
            session.setAttribute(Constants.USER_SESSION, updatedUser);
            redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, messageSource.getMessage(
                    Constants.PROFILE_UPDATE_SUCCESS, new Object[]{}, locale));
        } catch (InstanceNotFoundException ex) {
            return errorHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        } catch (AuthenticationException ex) {
            return errorHandlingUtils.handleAuthenticationException(ex, user.getEmail(), 
                    Constants.PASSWORD_PAGE, model, locale);
        }
        return Constants.SEND_REDIRECT + Constants.ROOT_ENDPOINT;
    }

    @GetMapping(Constants.USER_PROFILE_IMAGE_ENDPOINT)
    public ResponseEntity<byte[]> doGetProfileImage(@SessionAttribute(Constants.USER_SESSION) User user,
                                                    HttpServletResponse response,
                                                    Locale locale, 
                                                    Model model) {
        try {
            
            response.setHeader(Constants.CONTENT_TYPE_HEADER, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(Constants.CONTENT_DISPOSITION_HEADER,
                    MessageFormat.format(Constants.CONTENT_DISPOSITION_HEADER_VALUE, user.getEmail(), user.getImage()));
            
            byte[] contents = userService.getImage(user.getUserId());
            if (contents == null) {
                String message = messageSource.getMessage(Constants.INVALID_PROFILE_IMAGE_MESSAGE,
                        new Object[]{}, locale);
                model.addAttribute(Constants.MESSAGE, message);
                return new ResponseEntity<>(new byte[0], null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(contents, null, HttpStatus.OK);
        } catch (InstanceNotFoundException ex) {
            errorHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
            return new ResponseEntity<>(new byte[0], null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            errorHandlingUtils.handleUnexpectedException(ex, model);
            return new ResponseEntity<>(new byte[0], null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(Constants.USER_PROFILE_IMAGE_REMOVE_ENDPOINT)
    public String doRemoveProfileImage(@SessionAttribute(Constants.USER_SESSION) User user,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes,
                                       Locale locale, 
                                       Model model) {

        User updatedUser;
        try {
            updatedUser = userService.removeImage(user.getUserId());
            session.setAttribute(Constants.USER_SESSION, updatedUser);
            redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, messageSource.getMessage(
                    Constants.PROFILE_UPDATE_SUCCESS, new Object[]{}, locale));
        } catch (InstanceNotFoundException ex) {
            return errorHandlingUtils.handleInstanceNotFoundException(ex, model, locale);
        } catch (ServiceException ex) {
            return errorHandlingUtils.handleUnexpectedException(ex, model);
        }
        return Constants.SEND_REDIRECT + Constants.USER_PROFILE_ENDPOINT;
    }

    @PostMapping(Constants.SEND_EMAIL_ENDPOINT)
    public String doSendEmail(@RequestParam(Constants.EMAIL_PARAM) String email,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              Locale locale, 
                              Model model) {
        try {
            if(email == null || email.trim().length() == 0) {
                String message = messageSource.getMessage(Constants.INVALID_EMAIL_MESSAGE, new Object[]{}, locale);
                model.addAttribute(Constants.ERROR_MESSAGE, message);
                return Constants.SEND_EMAIL_PAGE;
            }
            
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            Integer portNumber = request.getServerPort();
            String contextPath = request.getContextPath();
            
            userService.sendResetPasswordEmail(email, MessageFormat.format(Constants.URL_FORMAT, scheme, 
                    serverName, portNumber.toString(), contextPath, Constants.RESET_PASSWORD_ENDPOINT), locale);
            
            redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, messageSource.getMessage(
                    Constants.MAIL_SUCCESS_MESSAGE, new Object[] { email }, locale));
            
        } catch (AuthenticationException ex) {
            return errorHandlingUtils.handleAuthenticationException(ex, email, 
                    Constants.SEND_EMAIL_PAGE, model, locale);
        } catch (Exception ex) {
            return errorHandlingUtils.handleUnexpectedException(ex, model);
        }
        return Constants.SEND_REDIRECT + Constants.SEND_EMAIL_ENDPOINT;
    }
    
    @PostMapping(Constants.RESET_PASSWORD_ENDPOINT)
    public String doResetPassword(@Valid @ModelAttribute(Constants.RESET_PASSWORD_FORM) ResetPasswordForm passwordForm,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request,
                                  Locale locale, 
                                  Model model) {
        try {
            if (result.hasErrors()) {
                errorHandlingUtils.handleInvalidFormError(result, 
                    Constants.RESET_PASSWORD_INVALID_PARAMS_MESSAGE, model, locale);
                return Constants.RESET_PASSWORD_PAGE;
            }
            userService.changePassword(passwordForm.getEmail(), passwordForm.getPassword(), passwordForm.getToken());
            redirectAttributes.addFlashAttribute(Constants.SUCCESS_MESSAGE, messageSource.getMessage(
                    Constants.CHANGE_PASSWORD_SUCCESS, new Object[0], locale));
        } catch (AuthenticationException ex) {
            return errorHandlingUtils.handleAuthenticationException(ex, passwordForm.getEmail(), 
                    Constants.SEND_EMAIL_PAGE, model, locale);
        } catch (Exception ex) {
            return errorHandlingUtils.handleUnexpectedException(ex, model);
        }
        return Constants.SEND_REDIRECT + Constants.ROOT_ENDPOINT;
    }

}
