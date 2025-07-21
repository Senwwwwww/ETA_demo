//package com.example.EAT_demo.server;
//
//import com.example.EAT_demo.Response;
//import com.example.EAT_demo.dao.EmployeeRepository;
//import com.example.EAT_demo.dao.PunchTimeRegulationsRepository;
//import com.example.EAT_demo.dto.AttendanceRequestDTO;
//import com.example.EAT_demo.exception.AttendanceContextException;
//import com.example.EAT_demo.exception.TcpServerContextException;
//import com.example.EAT_demo.exception.UserAuthenticationException;
//import com.example.EAT_demo.pojo.AttendanceInformation;
//import com.example.EAT_demo.dao.AttendanceInformationRepository;
//import com.example.EAT_demo.pojo.Employee;
//import com.example.EAT_demo.pojo.PunchTimeRegulations;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.time.LocalTime;
//import java.util.Optional;
//
//@SuppressWarnings("all")
//@Component
//public class TCPServer {
//
//    private static final int PORT = 8900;
//
//    @Autowired
//    private AttendanceInformationRepository attendanceInformationRepository;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private PunchTimeRegulationsRepository punchTimeRegulationsRepository;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    public void startServer() {
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            System.out.println("Server is listening on port " + PORT);
//
//            while (true) {
//                Socket socket = serverSocket.accept();
//                System.out.println("New client connected: " + socket.getInetAddress().getHostAddress());
//
//                new ClientHandler(socket).start();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private class ClientHandler extends Thread {
//        private Socket socket;
//
//        public ClientHandler(Socket socket) {
//            this.socket = socket;
//        }
//
//        @Override
//        public void run() {
//            try (
//                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
//            ) {
//                StringBuilder clientMessageBuilder = new StringBuilder();
//                int character;
//                int braceCount = 0;  // 用于跟踪大括号的数量
//
//                // 逐字节读取客户端传来的数据
//                while ((character = in.read()) != -1) {
//                    char currentChar = (char) character;
//                    clientMessageBuilder.append(currentChar);
//
//                    if (currentChar == '{') {
//                        braceCount++;  // 增加打开的括号计数
//                    } else if (currentChar == '}') {
//                        braceCount--;  // 减少打开的括号计数
//                    }
//
//                    // 如果 braceCount 为 0，表示已经读取到完整的 JSON 对象
//                    if (braceCount == 0) {
//                        String clientMessage = clientMessageBuilder.toString();
//                        System.out.println("Received complete message from client: " + clientMessage);
//
//                        // 将 JSON 字符串转换为 JsonNode 以解析 method 字段
//                        JsonNode rootNode = objectMapper.readTree(clientMessage);
//                        int method = rootNode.get("Method").asInt(); // 获取 method 字段
//
//                        // 根据 method 字段的值调用不同的处理方法
//                        switch (method) {
//                            case 1://员工打卡
//                                handleClockIn(rootNode, out);
//                                break;
//
//                            case 2://绑定员工id
//                                handleUserBinding(rootNode, out);
//                                break;
//
//                            default:
//                                out.println("Error: Invalid method.");
//                                System.err.println("Invalid method: " + method);
//                                throw new TcpServerContextException("方法不存在");
//                        }
//
//                        // 重置 StringBuilder 以接收下一条消息
//                        clientMessageBuilder.setLength(0);
//                    }
//                }
//
//            } catch (IOException e) {
//                throw new TcpServerContextException("打卡出错 请重新打卡");
//            } finally {
//                try {
//                    if (socket != null) {
//                        socket.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        private void handleUserBinding(JsonNode rootNode, PrintWriter out) {
//            try {
//                // 处理用户绑定的逻辑
//                Long employeeId = rootNode.get("EmployeeId").asLong();
//                if (employeeId == null || employeeId == 0 || employeeRepository.findByEmployeeId(employeeId) == null) {
//                    out.println("Error: Employee ID is invalid or missing.");
//                    throw new UserAuthenticationException("员工ID错误");
//                }
//                Employee employee = employeeRepository.findByEmployeeId(employeeId);
//
//                // 构建响应对象
//                Response<Employee> response = Response.newSuccess(employee);
//
//                // 将响应对象转换为 JSON 字符串
//                String jsonResponse = objectMapper.writeValueAsString(response);
//                out.println(jsonResponse + "END");
//
//            } catch (UserAuthenticationException | IOException e) {;
//                System.err.println(e.getMessage());
//                out.println("Error: " + e.getMessage());
//                throw new UserAuthenticationException("e.getMessage()");
//            }
//        }
//
//        private void handleClockIn(JsonNode rootNode, PrintWriter out) {
//            try {
//
//                PunchTimeRegulations punchTimeRegulations = punchTimeRegulationsRepository.findRegulations();
//
//                // 将 JSON 转换为 AttendanceRequestDTO 对象
//                AttendanceRequestDTO attendanceRequestDTO = objectMapper.treeToValue(rootNode, AttendanceRequestDTO.class);
//
//                // 检查用户打卡逻辑
//                Long employeeId = attendanceRequestDTO.getEmployeeId();
//                Employee employee = employeeRepository.findByEmployeeId(employeeId);
//                if (employee == null)
//                {
//                    out.println("Error: Employee ID is invalid or missing.");
//                    throw new AttendanceContextException("未找到该用户");
//                }
//                // 检查是否已存在当天的打卡记录
//                Optional<AttendanceInformation> existingRecord = attendanceInformationRepository.findByEmployeeAndClockInDate(employee, attendanceRequestDTO.getClockInDate());
//
//                AttendanceInformation attendanceInformation;
//                if (existingRecord.isPresent()) {
//                    attendanceInformation = existingRecord.get();
//
//                    // 假设第一次打卡时，ClockInStartTime 为空
//                    if (attendanceInformation.getClockInStartTime() == null) {
//                        attendanceInformation.setClockInStartTime(attendanceRequestDTO.getClockInTime());
//                    } else {
//                        // 如果已经有打卡开始时间，更新结束时间
//                        attendanceInformation.setClockInEndTime(attendanceRequestDTO.getClockInTime());
//                    }
//
//                    LocalTime ClockInStartTime = attendanceInformation.getClockInStartTime().toLocalTime();
//                    LocalTime ClockInEndTime = attendanceInformation.getClockInEndTime().toLocalTime();
//
//                    LocalTime RegulationStartTime = punchTimeRegulations.getClockInTime().toLocalTime();
//                    LocalTime RegulationEndTime = punchTimeRegulations.getClockOutTime().toLocalTime();
//                    LocalTime RegulationLateTime = punchTimeRegulations.getClockBeLate().toLocalTime();
//
//                    if (ClockInStartTime != null) {
//                        attendanceInformation.setClockInEndTime(attendanceRequestDTO.getClockInTime());
//
//                        if (ClockInStartTime.isAfter(RegulationStartTime.minusHours(1))
//                                && ClockInStartTime.isBefore(RegulationStartTime)
//                                && ClockInEndTime.isAfter(RegulationEndTime))
//                        {
//                            attendanceInformation.setClockInStatus("成功打卡");
//                        }
//                        else if (ClockInStartTime.isAfter(RegulationStartTime)
//                                && ClockInStartTime.isBefore(RegulationLateTime)
//                                && ClockInEndTime.isAfter(RegulationEndTime))
//                        {
//                            attendanceInformation.setClockInStatus("已迟到");
//
//                        } else if (ClockInStartTime.isAfter(RegulationStartTime.minusHours(1))
//                                && ClockInStartTime.isBefore(RegulationStartTime)
//                                && ClockInEndTime == null)
//                        {
//
//                            attendanceInformation.setClockInStatus("已打卡未签退");
//
//                        } else if (ClockInStartTime.isAfter(RegulationStartTime)
//                                && ClockInStartTime.isBefore(RegulationLateTime)
//                                && ClockInEndTime.isBefore(RegulationEndTime))
//                        {
//                            attendanceInformation.setClockInStatus("早退");
//                        } else {
//                            attendanceInformation.setClockInStatus("缺勤");
//                        }
//                    } else {
//                        attendanceInformation.setClockInStartTime(attendanceRequestDTO.getClockInTime());
//                    }
//
//                } else {
//                    attendanceInformation = new AttendanceInformation();
//                    attendanceInformation.setEmployee(employee);
//                    attendanceInformation.setClockInDate(attendanceRequestDTO.getClockInDate());
//                    attendanceInformation.setClockInStartTime(attendanceRequestDTO.getClockInTime());
//
//                    LocalTime ClockInStartTime = attendanceInformation.getClockInStartTime().toLocalTime();
//
//                }
//
//                attendanceInformationRepository.save(attendanceInformation);
//
//                Response<Employee> response = Response.newSuccess(employee);
//                String jsonResponse = objectMapper.writeValueAsString(response);
//                out.println(jsonResponse + "END");
//
//            } catch (IOException | UserAuthenticationException e) {
//                System.err.println(e.getMessage());
//                out.println("Error: " + e.getMessage());
//            }
//        }
//    }
//}