package com.example.EAT_demo.service;

import com.example.EAT_demo.Util.TokenUtil;
import com.example.EAT_demo.converter.EmployeeConverter;
import com.example.EAT_demo.converter.PersonalInformationConvert;
import com.example.EAT_demo.converter.UserConverter;
import com.example.EAT_demo.dao.*;
import com.example.EAT_demo.dto.PersonalDTO;
import com.example.EAT_demo.dto.UserDTO;
import com.example.EAT_demo.exception.EmailAuthExpiredException;
import com.example.EAT_demo.exception.PassowordAuthExpiredException;
import com.example.EAT_demo.exception.UserAlreadyExistsException;
import com.example.EAT_demo.exception.UserAuthenticationException;
import com.example.EAT_demo.pojo.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service
public class UserServiceImpl implements UserService {

    @Resource
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PersonalInformationRepository personalInformationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TokenUtil tokenUtil;

    //通过username获取
    @Override
    public UserDTO getUserByUsername(String username) {
         User user = userRepository.findByUsername(username);
        if (user.getUserIsDelete()== 1)
        {
            throw new IllegalStateException("Username: " + user.getUsername() + " has been delete");
        }
        return  UserConverter.convertUser(user);
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findByUserId(id);
        if (user == null) {
            throw new UserAuthenticationException("未找到该用户");
        }
        return UserConverter.convertUser(user);
    }

    @Override
    public UserDTO getUserAndPersonalInformationById(Long user_id) {
        User user = userRepository.findByUserId(user_id);
        PersonalInformation personalInformation;
        personalInformation = personalInformationRepository.findByuserId(user_id);
        if (personalInformation == null) {
            return UserConverter.convertUser(user);
        }
        user.setPersonalInformation(personalInformation);
        userRepository.save(user);
        return UserConverter.convertUserandPersonalInfomation(user);
    }

    @Override
    public PersonalDTO getUserAllInfoById(Long user_id) {
        User user = userRepository.findByUserId(user_id);
        PersonalInformation personalInformation;
        Employee employee=employeeRepository.findByUserId(user_id);
        Department department;

        personalInformation = personalInformationRepository.findByuserId(user_id);
        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setName(user.getName());
        personalDTO.setEmail(user.getEmail());
        personalDTO.setUsername(user.getUsername());
        personalDTO.setRole(user.getRole());
        if(employee!=null) {
            personalDTO.setDepartment(employee.getDepartment());
        }
        personalDTO.setPassword(user.getPassword());
        personalDTO.setUserIsDelete(user.getUserIsDelete());
        personalDTO.setUserId(user_id);

        if(personalInformation == null){
            return personalDTO;
        }
        personalDTO.setPersonalInformationDTO(PersonalInformationConvert.convertPersonalInformation(personalInformation));
        employee = employeeRepository.findByUserId(user_id);
        if (employee == null){
            return personalDTO;
        }
        personalDTO.setEmployeeDTO(EmployeeConverter.convertEmployee(employee));
        return personalDTO;
    }


    //返回用户所有信息
    @Override
    public List<UserDTO> getAllUserInfo() {
        List<User> users = userRepository.findAll();  // 获取所有用户
        for (User user : users) {
            // 查询并设置个人信息
            PersonalInformation personalInformation = personalInformationRepository.findByuserId(user.getUserId());
            if (personalInformation != null) {
                user.setPersonalInformation(personalInformation);
            }

            // 查询并设置员工信息
            Employee employee = employeeRepository.findByUserId(user.getUserId());
            if (employee != null) {
                user.setEmployee(employee);
            }
        }
        // 转换并返回所有用户信息
        return users.stream()
                .map(UserConverter::convertUserAllInfo)
                .collect(Collectors.toList());
    }


    @Override
    public long addNewUser(UserDTO userDTO) {
        User userINDB = userRepository.findByUsername(userDTO.getUsername());
        if (userINDB != null) {
            throw new IllegalStateException("Username: " + userDTO.getUsername() + " has been taken");
        }

        User user = UserConverter.convertUser(userDTO);
//        System.out.println("Username: " + user.getUsername());
        userRepository.save(user);
        return user.getUserId();
    }


    @Override
    public Long deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("ID: " + id + " doesn't exist"));
        userRepository.delete(user);
        return user.getUserId();
    }

    @Override
    public UserDTO deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        user.setUserIsDelete(0);
        userRepository.save(user);
        return UserConverter.convertUser(user);
    }

    @Transactional
    @Override
    public UserDTO updateUserById(long id, String username, String password,String email ,String name) {
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("ID: " + id + " doesn't exist"));

        // 更新用户名
        if (StringUtils.hasLength(username) && !userInDB.getUsername().equals(username)) {
            userInDB.setUsername(username);
        }

        // 更新密码
        if (StringUtils.hasLength(password) && !userInDB.getPassword().equals(password)) {
            userInDB.setPassword(password);
        }


        // 更新电子邮件
        if (StringUtils.hasLength(email)) {
            if (userInDB.getEmail() == null || userInDB.getEmail().isEmpty()) {
                userInDB.setEmail(email);
            } else if (!userInDB.getEmail().equals(email)) {
                userInDB.setEmail(email);
            }
        }

        //更新用户名称
        if (StringUtils.hasLength(name)) {
            if (userInDB.getName() == null || userInDB.getName().isEmpty()) {
                userInDB.setName(name);
            } else if (!userInDB.getEmail().equals(name)) {
                userInDB.setName(name);
            }
        }
        User user = userRepository.save(userInDB);

        return UserConverter.convertUser(user);
    }

    //登录
    @Override
    public String loginUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UserAuthenticationException("用户未注册");
        }

        if (user.getEmail() == null) {
            userRepository.delete(user);
            throw new UserAuthenticationException("邮箱未绑定,请重新注册");
        }

        if (user.getUserIsDelete()==1) {
            throw new UserAuthenticationException("用户未注册");
        }

        // 检查密码是否匹配
        if (!user.getPassword().equals(userDTO.getPassword())) {
            throw new UserAuthenticationException("账户或密码错误");
        }

        if (user.getRole() == null) {
            user.setRole(roleRepository.findById(0L).orElseThrow(() -> new RuntimeException("Default role not found")));
            userRepository.save(user);
        }

        // 生成 token
        return tokenUtil.getToken(user.getUsername(), user.getRole().getRoleName());
    }

    //注册
    @Override
    public long registerUser(UserDTO userDTO) {
        User userINDB = userRepository.findByUsername(userDTO.getUsername());

        if (userINDB != null) {
            throw new UserAlreadyExistsException("用户名已被使用");
        }
        User user = UserConverter.convertUser(userDTO);
        userRepository.save(user);
        return user.getUserId();
    }


    //找回密码
    @Override
    public long RecoverPassword(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UserAuthenticationException("用户未注册");
        }

        if (user.getEmail() == null) {
            throw new UserAuthenticationException("邮箱未绑定无法找回");
        }

        if (user.getUserIsDelete()==1) {
            throw new UserAuthenticationException("用户未注册");
        }

        // 检查邮箱是否匹配
        if (!user.getEmail().equals(userDTO.getEmail())){
            throw new UserAuthenticationException("账户或邮箱错误");
        }

        // 生成 token
        return user.getUserId();
    }


    //邮箱验证
    @Transactional
    @Override
    public String EmailVerification(long id, String email) {
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("ID: " + id + " doesn't exist"));
        // 更新电子邮件
        if (StringUtils.hasLength(email)) {
            if (userInDB.getEmail() == null || userInDB.getEmail().isEmpty()) {
                throw new EmailAuthExpiredException("邮箱未注册");
            } else if (!userInDB.getEmail().equals(email)) {
               throw new EmailAuthExpiredException("邮箱输入错误");
            }
        }
        return "邮箱验证成功";
    }

    //更新密码
    @Transactional
    @Override
    public UserDTO updateUserPasswordById(long id, String oldPassword, String password) {
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("ID: " + id + " doesn't exist"));

        // 更新密码
        if (StringUtils.hasLength(oldPassword) && StringUtils.hasLength(password)) {
            if (userInDB.getPassword() == null || userInDB.getPassword().isEmpty()) {
                userInDB.setPassword(password);
            } else if (userInDB.getPassword().equals(password)) {
                userInDB.setPassword(password);
            } else{
                throw new PassowordAuthExpiredException("旧密码错误");
            }
        }

        User user = userRepository.save(userInDB);

        return UserConverter.convertUser(user);
    }


    //用户绑定员工
    @Override
    public UserDTO UserBind(Long user_id, Long employee_id, Long department_id, String realName) {
        User user = userRepository.findByUserId(user_id);
        Employee employee;
        Department department;

        if (user.getEmployee() != null) {
            throw new UserAuthenticationException("用户已绑定员工");
        }

        employee = employeeRepository.findByEmployeeId(employee_id);
        if (employee == null) {
            throw new UserAuthenticationException("员工ID错误");
        } else if (!employee.getRealName().equals(realName)) {
            throw new UserAuthenticationException("员工ID与姓名不匹配");
        }
        user.setEmployee(employee);
        employee.setUserId(user_id);

        department = departmentRepository.findByDepartmentId(department_id);
        if (department == null) {
            throw new UserAuthenticationException("部门名称错误");
        }

        userRepository.save(user);
        return UserConverter.convertUserAllInfo(user);
    }

}