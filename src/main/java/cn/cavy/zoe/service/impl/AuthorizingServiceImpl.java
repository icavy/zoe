package cn.cavy.zoe.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.cavy.zoe.entity.User;
import cn.cavy.zoe.exception.LoginException;
import cn.cavy.zoe.mapper.PermissionMapper;
import cn.cavy.zoe.mapper.UserMapper;
import cn.cavy.zoe.service.AuthorizingService;

@Service("authorizingService")
public class AuthorizingServiceImpl extends AuthorizingRealm implements AuthorizingService {

    public static Logger logger = LoggerFactory.getLogger(AuthorizingServiceImpl.class);

    public static final String HASH_ALGORITHM_MD5 = "MD5";

    public static final String HASH_ALGORITHM_SHA1 = "SHA-1";

    public static final String HASH_ALGORITHM_SHA256 = "SHA-256";

    @Resource
    UserMapper userMapper;

    @Resource
    PermissionMapper permissionMapper;

    public AuthorizingServiceImpl() {
        new Md5Hash();

        setCredentialsMatcher(new HashedCredentialsMatcher(HASH_ALGORITHM_SHA1));
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.fromRealm(getName()).iterator().next();
        if (username != null) {
            // 查询用户授权信息
            Collection<String> pers = permissionMapper.queryPermissions(username);
            if (pers != null && !pers.isEmpty()) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                for (String each : pers)
                    info.addStringPermission(each);

                return info;
            }
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 通过表单接收的用户名
        String username = token.getUsername();

        if (username != null && !"".equals(username)) {
            User account = userMapper.getByLoginName(username);

            if (account != null) {
                return new SimpleAuthenticationInfo(account.getEmail(), account.getPassword(), getName());
            }
        }

        return null;
    }

    public void login(String loginName, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        token.setRememberMe(true);

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            logger.error(uae.getMessage(), uae);
            
            throw new LoginException(LoginException.UNKNOWN_ACCOUNT);
        } catch (IncorrectCredentialsException ice) {
            logger.error(ice.getMessage(), ice);
            
            throw new LoginException(LoginException.INCORRECT_CREDENTIALS);
        } catch (LockedAccountException lae) {
            logger.error(lae.getMessage(), lae);
            
            throw new LoginException(LoginException.LOCKED_ACCOUNT);
        } catch (ExcessiveAttemptsException eae) {
            logger.error(eae.getMessage(), eae);
            
            throw new LoginException(LoginException.EXCESSIVE_ATTEMPTS);
        } catch (AuthenticationException ae) {
            logger.error(ae.getMessage(), ae);
            
            throw new LoginException(LoginException.AUTHENTICATION);
        }
    }
}
