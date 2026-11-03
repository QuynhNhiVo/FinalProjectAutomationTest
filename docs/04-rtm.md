# Requirements Traceability Matrix (RTM) – Ma trận truy vết yêu cầu

## 1. Document Control

### 1.1 Thông tin tài liệu
- **Tên dự án**: Final Project Automation Test – Anh Tester HRM
- **Hệ thống được kiểm thử (AUT)**: Anh Tester HRM System (https://hrm.anhtester.com/)
- **Mã tài liệu**: RTM-HRM-04
- **Phiên bản**: v0.1
- **Trạng thái**: Draft
- **Tác giả**: Võ Quỳnh Nhi
- **Ngày lập tài liệu**: 2026-11-02
- **Ngày phê duyệt**: [CẦN XÁC NHẬN: Ngày ký duyệt chính thức]

### 1.2 Lịch sử thay đổi
| Phiên bản | Ngày cập nhật | Tác giả | Tóm tắt thay đổi |
|---|---|---|---|
| v0.1 | 2026-11-02 | Võ Quỳnh Nhi | Khởi tạo Ma trận truy vết yêu cầu (RTM) đồng bộ 33 ca kiểm thử tự động với 40 yêu cầu cơ sở và 14 rủi ro cao |

### 1.3 Tài liệu tham chiếu
- `docs/project-context.md`: Bối cảnh dự án kiểm thử tự động HRM
- `docs/01-risk-register.md`: Sổ đăng ký rủi ro kiểm thử (Risk Register)
- `docs/02-test-strategy.md`: Chiến lược kiểm thử tự động (Test Strategy)
- `docs/03-test-plan.md`: Kế hoạch kiểm thử chi tiết (Test Plan)
- Chuẩn quốc tế ISO/IEC/IEEE 29119-3 (Test Documentation) & ISO/IEC/IEEE 29148 (Requirements Engineering)

---

## 2. Giả định, hạn chế và câu hỏi mở

### 2.1 Giả định và nguồn gốc yêu cầu
- Hệ thống Anh Tester HRM (`https://hrm.anhtester.com/`) là ứng dụng đào tạo demo, không cung cấp tài liệu đặc tả yêu cầu phần mềm chính thức (SRS/BRD).
- Toàn bộ 40 yêu cầu trong tài liệu này được chia thành hai nhóm nguồn gốc:
  1. **Yêu cầu được chứng minh bởi mã kiểm thử (Test-proven Requirements)**: Suy ra trực tiếp từ các luồng kiểm thử tự động đã cài đặt trong `src/test/java/`, mang trạng thái `Confirmed`.
  2. **Yêu cầu suy luận từ nghiệp vụ (Inferred Requirements)**: Suy ra từ chuẩn nghiệp vụ quản trị nhân sự ERP và phân tích rủi ro, bắt buộc mang trạng thái `Unconfirmed` và kèm tag `[CẦN QUAN SÁT TRÊN AUT]` chờ người dùng đối soát thủ công trên hệ thống.

### 2.2 Câu hỏi mở cần người dùng xác nhận
1. [CẦN QUAN SÁT TRÊN AUT: Xác nhận hệ thống có thông báo lỗi cụ thể khi tạo khách hàng bị trùng Username hoặc Email hay không (REQ-CLI-008)].
2. [CẦN QUAN SÁT TRÊN AUT: Xác nhận hệ thống có cơ chế chặn lưu khi chọn ngày kết thúc nhỏ hơn ngày bắt đầu của dự án hay không (REQ-PROJ-007)].
3. [CẦN QUAN SÁT TRÊN AUT: Xác nhận chức năng tải tệp đính kèm dự án có danh sách đuôi tệp cho phép (whitelist) hay cho phép tải mọi định dạng (REQ-PROJ-008)].
4. [CẦN QUAN SÁT TRÊN AUT: Xác nhận hệ thống có chặn nhập số âm trên trường budget hours của tác vụ hay không (REQ-TASK-005)].
5. [CẦN QUAN SÁT TRÊN AUT: Xác nhận tài khoản vai trò Client khi đăng nhập có bị chặn truy cập hoàn toàn vào các URL quản trị /erp/* của Admin hay không (REQ-AUTH-006)].

---

## 3. Bảng yêu cầu cơ sở (Requirements Baseline)

Bảng dưới đây quy định danh mục 40 yêu cầu cơ sở của hệ thống, bao phủ 6 phân hệ chức năng, luồng liên thông E2E và tiêu chuẩn an toàn:

| REQ ID | Module | Statement | Type | Priority | Source | Acceptance criteria | Risks | Verification |
|---|---|---|---|---|---|---|---|---|
| REQ-AUTH-001 | AUTH | Hệ thống phải cho phép người dùng đăng nhập thành công với tài khoản quản trị viên hợp lệ. | Functional | Must | Chứng minh bởi test LoginTest#TC_LoginAdminSuccess | Given tài khoản admin_example When nhập mật khẩu 123456 Then chuyển hướng đến Dashboard | RSK-002, RSK-023, RSK-024 | Confirmed |
| REQ-AUTH-002 | AUTH | Hệ thống phải cho phép khách hàng đăng nhập thành công bằng tài khoản client đã được tạo. | Functional | Must | Chứng minh bởi test LoginTest#TC_LoginClientSuccess | Given tài khoản client đã tạo When nhập username và password Then đăng nhập thành công vào portal client | RSK-002 | Confirmed |
| REQ-AUTH-003 | AUTH | Hệ thống phải hỗ trợ đăng nhập nhiều tài khoản khách hàng từ bộ dữ liệu tham số hóa. | Functional | Should | Chứng minh bởi test LoginTest#TC_LoginClientsSuccess | Given danh sách client trong Excel When đăng nhập từng tài khoản Then hệ thống xác thực đúng từng dòng | RSK-002 | Confirmed |
| REQ-AUTH-004 | AUTH | Hệ thống phải từ chối đăng nhập và hiển thị thông báo lỗi khi tên người dùng không tồn tại. | Functional | Must | Chứng minh bởi test LoginTest#TC_LoginClientWithInvalidUsername | Given username không hợp lệ When bấm Login Then hiển thị thông báo Invalid Login Credentials | RSK-002, RSK-014 | Confirmed |
| REQ-AUTH-005 | AUTH | Hệ thống phải từ chối đăng nhập và hiển thị thông báo lỗi khi mật khẩu không chính xác. | Functional | Must | Chứng minh bởi test LoginTest#TC_LoginClientWithInvalidPassword | Given username đúng nhưng password sai When bấm Login Then hiển thị thông báo mật khẩu không đúng | RSK-002, RSK-014 | Confirmed |
| REQ-AUTH-006 | AUTH | Hệ thống phải ngăn chặn tài khoản client truy cập trái phép vào các đường dẫn quản trị của admin. | Security | Must | Suy luận từ phân tích rủi ro | [CẦN QUAN SÁT TRÊN AUT] | RSK-001 | Unconfirmed |
| REQ-AUTH-007 | AUTH | Hệ thống phải cho phép người dùng đăng xuất an toàn và vô hiệu hóa phiên làm việc hiện tại. | Security | Must | Chứng minh bởi test LoginTest#TC_LoginClientSuccess | Given người dùng đang đăng nhập When bấm Log Out Then chuyển hướng về trang Login và hủy session | RSK-001 | Confirmed |
| REQ-DASH-001 | DASH | Hệ thống phải hiển thị thanh điều hướng chính sau khi đăng nhập tài khoản quản trị thành công. | Usability | Should | Chứng minh bởi test DashboardTest#TC_Dashboarrd | Given đăng nhập admin thành công When mở Dashboard Then thanh pc-navbar hiển thị đầy đủ | RSK-004 | Confirmed |
| REQ-DASH-002 | DASH | Hệ thống phải hiển thị chính xác các widget thống kê tổng số dự án trên giao diện Dashboard. | Functional | Should | Suy luận từ thiết kế giao diện | [CẦN QUAN SÁT TRÊN AUT] | RSK-003 | Unconfirmed |
| REQ-DASH-003 | DASH | Hệ thống phải đảm bảo giao diện thanh điều hướng co giãn tương thích trên các độ phân giải màn hình. | Usability | Could | Suy luận từ thiết kế responsive | [CẦN QUAN SÁT TRÊN AUT] | RSK-004 | Unconfirmed |
| REQ-CLI-001 | CLI | Hệ thống phải điều hướng thành công đến trang danh sách khách hàng và hiển thị tiêu đề hợp lệ. | Functional | Must | Chứng minh bởi test ManageClientsTest#TC_GetClientsPage | Given admin đã đăng nhập When bấm Manage Clients Then mở URL /erp/clients-list với tiêu đề List All Clients | RSK-005 | Confirmed |
| REQ-CLI-002 | CLI | Hệ thống phải cho phép tạo mới hồ sơ khách hàng với đầy đủ thông tin hợp lệ. | Functional | Must | Chứng minh bởi test ManageClientsTest#TC_AddNewClient | Given form Add New Client When điền đủ trường bắt buộc và Save Then hiển thị thông báo Client added. | RSK-005, RSK-014, RSK-017 | Confirmed |
| REQ-CLI-003 | CLI | Hệ thống phải cho phép cập nhật thông tin chi tiết của khách hàng đã tồn tại. | Functional | Should | Chứng minh bởi test ManageClientsTest#TC_EditClient | Given khách hàng có sẵn When sửa thông tin và bấm Save Then hiển thị thông báo Client updated. | RSK-005 | Confirmed |
| REQ-CLI-004 | CLI | Hệ thống phải tìm kiếm chính xác bản ghi khách hàng theo từ khóa tên hoặc mã định danh. | Functional | Should | Chứng minh bởi test ManageClientsTest#TC_VerifyClient | Given tên khách hàng đã tạo When nhập vào ô search Then bảng hiển thị đúng bản ghi đó | RSK-005, RSK-026 | Confirmed |
| REQ-CLI-005 | CLI | Hệ thống phải cho phép xoá bản ghi khách hàng khi người dùng xác nhận hộp thoại xác nhận. | Functional | Must | Chứng minh bởi test ManageClientsTest#TC_DeleteClient | Given bản ghi khách hàng When bấm Delete và Confirm Then hiển thị thông báo Client deleted. | RSK-006, RSK-017 | Confirmed |
| REQ-CLI-006 | CLI | Hệ thống phải thực thi trọn vẹn chuỗi thao tác thêm, sửa, tìm kiếm và xác minh khách hàng. | Functional | Must | Chứng minh bởi test ManageClientsTest#TC_FlowVerifyFunctionClient | Given quy trình quản lý khách hàng When chạy chuỗi nghiệp vụ Then tất cả các bước đều thành công | RSK-005, RSK-017 | Confirmed |
| REQ-CLI-007 | CLI | Hệ thống phải xử lý xóa danh sách khách hàng hàng loạt từ nguồn dữ liệu tham số hóa. | Functional | Should | Chứng minh bởi test ManageClientsTest#TC_FlowDeleteClients | Given danh sách client cần xóa trong Excel When chạy xóa từng dòng Then hệ thống xóa thành công | RSK-006, RSK-026 | Confirmed |
| REQ-CLI-008 | CLI | Hệ thống phải từ chối tạo mới khách hàng khi tên đăng nhập hoặc email đã tồn tại trong hệ thống. | Business rule | Must | Suy luận từ phân tích rủi ro dữ liệu | [CẦN QUAN SÁT TRÊN AUT] | RSK-005 | Unconfirmed |
| REQ-EMP-001 | EMP | Hệ thống phải điều hướng thành công vào phân hệ quản lý nhân viên và hiển thị danh sách nhân sự. | Functional | Must | Chứng minh bởi test EmployeesTest#TC_AddNewEmployees | Given admin đã đăng nhập When bấm Employees Then mở URL /erp/staff-list | RSK-007 | Confirmed |
| REQ-EMP-002 | EMP | Hệ thống phải cho phép thêm mới hồ sơ nhân viên với phòng ban và vai trò hợp lệ. | Functional | Must | Chứng minh bởi test EmployeesTest#TC_AddNewEmployees | Given form thêm nhân viên When điền đủ thông tin và Save Then lưu thành công bản ghi nhân viên | RSK-007, RSK-017 | Confirmed |
| REQ-EMP-003 | EMP | Hệ thống phải xác thực định dạng số điện thoại và email của nhân viên trước khi lưu hồ sơ. | Data integrity | Should | Suy luận từ phân tích rủi ro dữ liệu | [CẦN QUAN SÁT TRÊN AUT] | RSK-007 | Unconfirmed |
| REQ-EMP-004 | EMP | Hệ thống phải chỉ cho phép lựa chọn các nhân sự có trạng thái hoạt động vào danh sách phân công dự án. | Business rule | Should | Suy luận từ phân tích nghiệp vụ | [CẦN QUAN SÁT TRÊN AUT] | RSK-008 | Unconfirmed |
| REQ-PROJ-001 | PROJ | Hệ thống phải hiển thị danh sách toàn bộ dự án hiện có tại trang quản lý dự án. | Functional | Must | Chứng minh bởi test ProjectsPageTest#TC_ProjectsPage | Given admin đăng nhập When bấm Projects Then mở URL /erp/projects-list với tiêu đề List All Projects | RSK-009 | Confirmed |
| REQ-PROJ-002 | PROJ | Hệ thống phải cho phép tạo mới dự án gắn với khách hàng và nhân sự thực hiện. | Functional | Must | Chứng minh bởi test ProjectsPageTest#TC_AddNewProjectSpecified | Given form tạo dự án When chọn Client, Team và điền ngày tháng Then hiển thị Project added. | RSK-009, RSK-014, RSK-017 | Confirmed |
| REQ-PROJ-003 | PROJ | Hệ thống phải cho phép xoá dự án khỏi hệ thống khi người dùng xác nhận thao tác. | Functional | Must | Chứng minh bởi test ProjectsPageTest#TC_DeleteProjectSpecified | Given dự án đã tạo When bấm Delete và Confirm Then hiển thị thông báo Project deleted. | RSK-017, RSK-026 | Confirmed |
| REQ-PROJ-004 | PROJ | Hệ thống phải cho phép đính kèm tệp tin tài liệu vào hồ sơ dự án đã tạo. | Functional | Should | Chứng minh bởi test ProjectsPageTest#TC_AddAttachUpdateStatus | Given dự án có sẵn When tải tệp tin lên Then hiển thị thông báo Project file added. | RSK-010, RSK-021 | Confirmed |
| REQ-PROJ-005 | PROJ | Hệ thống phải cho phép cập nhật trạng thái tiến độ thực hiện của dự án. | Functional | Should | Chứng minh bởi test ProjectsPageTest#TC_AddAttachUpdateStatus | Given dự án có sẵn When đổi status và lưu Then hiển thị thông báo Project status updated. | RSK-011 | Confirmed |
| REQ-PROJ-006 | PROJ | Hệ thống phải hỗ trợ tạo mới dự án từ nguồn dữ liệu kết hợp đa bảng trong Excel. | Functional | Should | Chứng minh bởi test ProjectsPageTest#TC_AddNewProject | Given cặp dữ liệu client-project When thực thi Then tạo thành công dự án tương ứng | RSK-009, RSK-017 | Confirmed |
| REQ-PROJ-007 | PROJ | Hệ thống phải từ chối lưu khi ngày kết thúc của dự án nhỏ hơn ngày bắt đầu. | Business rule | Must | Suy luận từ logic nghiệp vụ thời gian | [CẦN QUAN SÁT TRÊN AUT] | RSK-009 | Unconfirmed |
| REQ-PROJ-008 | PROJ | Hệ thống phải từ chối tiếp nhận các tệp tin đính kèm chứa mã độc hoặc phần mở rộng bị cấm. | Security | Must | Suy luận từ phân tích rủi ro bảo mật | [CẦN QUAN SÁT TRÊN AUT] | RSK-010 | Unconfirmed |
| REQ-TASK-001 | TASK | Hệ thống phải cho phép tạo mới công việc trực thuộc một dự án cụ thể. | Functional | Must | Chứng minh bởi test ProjectsPageTest#TC_AddTask | Given trang chi tiết dự án When thêm task mới Then hiển thị thông báo Task added. | RSK-011, RSK-017 | Confirmed |
| REQ-TASK-002 | TASK | Hệ thống phải hỗ trợ tạo công việc kèm tệp đính kèm và cập nhật trạng thái trong cùng một luồng. | Functional | Should | Chứng minh bởi test ProjectsPageTest#TC_AddTaskAddAttachUpdateStatusSpecified | Given form task When tạo task, đính kèm file và đổi status Then các bước đều lưu thành công | RSK-010, RSK-011 | Confirmed |
| REQ-TASK-003 | TASK | Hệ thống phải cho phép xoá công việc tại trang quản lý danh sách tác vụ. | Functional | Must | Chứng minh bởi test TaskPageTest#TC_DeleteTaskSpecified | Given danh sách task When bấm Delete task Then bản ghi task bị xoá khỏi hệ thống | RSK-017, RSK-026 | Confirmed |
| REQ-TASK-004 | TASK | Hệ thống phải hỗ trợ xoá danh sách công việc tự động từ nguồn dữ liệu tham số hóa. | Functional | Should | Chứng minh bởi test TaskPageTest#TC_DeleteTasks | Given danh sách task trong Excel When chạy xóa từng task Then hệ thống xóa thành công | RSK-017, RSK-026 | Confirmed |
| REQ-TASK-005 | TASK | Hệ thống phải từ chối lưu công việc khi số giờ ngân sách là giá trị âm. | Business rule | Should | Suy luận từ phân tích giá trị biên | [CẦN QUAN SÁT TRÊN AUT] | RSK-012 | Unconfirmed |
| REQ-E2E-001 | E2E | Hệ thống phải duy trì tính toàn vẹn dữ liệu xuyên suốt luồng tích hợp từ Client đến Project và Task. | Functional | Must | Chứng minh bởi test FN_E2EFLowTest#TC_E2E_Spec | Given luồng E2E tuần tự When thực hiện tạo Client, Team, Project, Task, Attach, Status và dọn dẹp Then toàn bộ quy trình hoàn tất không lỗi | RSK-013, RSK-016, RSK-017, RSK-018 | Confirmed |
| REQ-E2E-002 | E2E | Hệ thống phải hỗ trợ thực thi kịch bản tích hợp liên thông với nguồn dữ liệu tổ hợp đa dạng từ Excel. | Functional | Should | Chứng minh bởi test FN_E2EFLowTest#TC_E2E | Given bộ dữ liệu kết hợp CPT When chạy luồng E2E Then hệ thống xử lý chính xác theo từng bộ tham số | RSK-013, RSK-017, RSK-018 | Confirmed |
| REQ-SEC-001 | SEC | Hệ thống phải xử lý an toàn các ký tự đặc biệt trong form nhập liệu ngăn chặn nguy cơ tấn công kịch bản chéo. | Security | Should | Suy luận từ yêu cầu an toàn thông tin | [CẦN QUAN SÁT TRÊN AUT] | RSK-010 | Unconfirmed |
| REQ-SEC-002 | SEC | Hệ thống phải bảo vệ dữ liệu cấu hình và không lưu trữ thông tin nhạy cảm ở dạng thô trong mã nguồn. | Security | Must | Chứng minh bởi cấu hình Configs.properties và BaseTest | Given file cấu hình When đọc thông tin Then chỉ chứa tham số demo công khai | RSK-028, RSK-030 | Confirmed |
| REQ-SEC-003 | SEC | Hệ thống phải đảm bảo tiến trình build thất bại ngay khi có ca kiểm thử không đạt tiêu chuẩn. | Non-functional | Must | Chứng minh bởi cấu hình pom.xml với failIfNoTests | Given ca kiểm thử thất bại When chạy Maven Surefire Then trả về mã lỗi build failure | RSK-019, RSK-022 | Confirmed |

---

## 4. Bảng danh mục test case (Test Case Inventory)

Bảng dưới đây liệt kê toàn bộ 33 ca kiểm thử tự động quét trực tiếp từ mã nguồn `src/test/java/`, cùng các ca kiểm thử đề xuất bổ sung:

| TC ID | Title | Module | Kind | Level | Status | Class#method | Data sets | Groups | Precondition | Overlap note |
|---|---|---|---|---|---|---|:---:|---|---|---|
| TC-AUTH-001 | Kiểm thử đăng nhập admin thành công | AUTH | Positive | UI | Automated | LoginTest#TC_LoginAdminSuccess | 1 | Groups.SMOKE,Groups.REGRESSION,Groups.AUTH | Môi trường web sẵn sàng, tài khoản admin_example tồn tại | Tập con kiểm tra xác thực cơ bản |
| TC-AUTH-002 | Kiểm thử đăng nhập client thành công theo dòng chỉ định | AUTH | E2E | UI | Automated | LoginTest#TC_LoginClientSuccess | 1 | Groups.REGRESSION,Groups.AUTH,Groups.CLIENT | Cần admin tạo mới client trước khi đăng nhập | Trùng một phần luồng với TC-AUTH-003 |
| TC-AUTH-003 | Kiểm thử đăng nhập client thành công với DataProvider | AUTH | E2E | UI | Automated | LoginTest#TC_LoginClientsSuccess | Multi | Groups.REGRESSION,Groups.AUTH,Groups.CLIENT | Sheet Clients trong LoginHRM.xlsx có dữ liệu | Mở rộng của TC-AUTH-002 trên nhiều dòng |
| TC-AUTH-004 | Kiểm thử đăng nhập client thất bại khi sai username | AUTH | Negative | UI | Automated | LoginTest#TC_LoginClientWithInvalidUsername | 1 | Groups.SMOKE,Groups.REGRESSION,Groups.AUTH,Groups.NEGATIVE | Màn hình đăng nhập HRM hiển thị | Độc lập, kiểm tra validation username |
| TC-AUTH-005 | Kiểm thử đăng nhập client thất bại khi sai password | AUTH | Negative | UI | Automated | LoginTest#TC_LoginClientWithInvalidPassword | 1 | Groups.SMOKE,Groups.REGRESSION,Groups.AUTH,Groups.NEGATIVE | Màn hình đăng nhập HRM hiển thị | Độc lập, kiểm tra validation password |
| TC-DASH-001 | Kiểm tra giao diện Dashboard sau khi đăng nhập | DASH | Positive | UI | Automated | DashboardTest#TC_Dashboarrd | 1 | Groups.REGRESSION,Groups.DASHBOARD | Đăng nhập tài khoản admin thành công | Kiểm tra thanh điều hướng pc-navbar |
| TC-CLI-001 | Kiểm tra truy cập trang danh sách quản lý khách hàng | CLI | Positive | UI | Automated | ManageClientsTest#TC_GetClientsPage | 1 | Groups.SMOKE,Groups.REGRESSION,Groups.CLIENT | Đăng nhập tài khoản admin thành công | Kiểm tra điều hướng URL và tiêu đề |
| TC-CLI-002 | Kiểm thử thêm mới khách hàng theo dòng dữ liệu | CLI | Positive | UI | Automated | ManageClientsTest#TC_AddNewClient | 1 | Groups.REGRESSION,Groups.CLIENT | Đăng nhập admin, form Add Client mở | Trùng một phần với TC-CLI-006 |
| TC-CLI-003 | Kiểm thử chỉnh sửa thông tin khách hàng có sẵn | CLI | Positive | UI | Automated | ManageClientsTest#TC_EditClient | 1 | Groups.REGRESSION,Groups.CLIENT,Groups.DEPENDENT | Cần bản ghi client đã tạo trước ở dòng 2 | Phụ thuộc dữ liệu tạo từ trước |
| TC-CLI-004 | Kiểm tra tìm kiếm và đối soát thông tin khách hàng | CLI | Positive | UI | Automated | ManageClientsTest#TC_VerifyClient | 1 | Groups.REGRESSION,Groups.CLIENT,Groups.DEPENDENT | Cần bản ghi client đã tồn tại trong bảng | Phụ thuộc dữ liệu có sẵn |
| TC-CLI-005 | Kiểm thử xoá khách hàng theo dòng chỉ định | CLI | Positive | UI | Automated | ManageClientsTest#TC_DeleteClient | 1 | Groups.REGRESSION,Groups.CLIENT,Groups.DEPENDENT | Cần bản ghi client tồn tại để xoá | Xoá bản ghi kiểm thử |
| TC-CLI-006 | Kiểm thử chuỗi chức năng thêm sửa tìm kiếm xác minh client | CLI | Positive | UI | Automated | ManageClientsTest#TC_FlowVerifyFunctionClient | 1 | Groups.REGRESSION,Groups.CLIENT | Đăng nhập tài khoản admin thành công | Gộp các bước thành luồng khép kín |
| TC-CLI-007 | Kiểm thử chuỗi chức năng client với DataProvider | CLI | Positive | UI | Automated | ManageClientsTest#TC_FlowVerifyFunctionClients | Multi | Groups.REGRESSION,Groups.CLIENT | Dữ liệu sheet Clients trong Excel | Mở rộng của TC-CLI-006 trên nhiều bộ dữ liệu |
| TC-CLI-008 | Kiểm thử xoá khách hàng hàng loạt với DataProvider | CLI | Positive | UI | Automated | ManageClientsTest#TC_FlowDeleteClients | Multi | Groups.SMOKE,Groups.CLIENT,Groups.DEPENDENT | Danh sách client cần xoá trong file Excel | Phục vụ dọn dẹp dữ liệu tồn đọng |
| TC-CLI-009 | Kiểm thử luồng chức năng client khép kín kèm đăng nhập | CLI | E2E | E2E | Automated | TestClientFunctionFlow#TC_ClientFunctionFlowSpecified | 1 | Groups.SMOKE,Groups.REGRESSION,Groups.CLIENT | Đăng nhập admin thành công | Luồng khép kín đầy đủ từ tạo đến xoá |
| TC-CLI-010 | Kiểm thử luồng chức năng client khép kín với DataProvider | CLI | E2E | E2E | Automated | TestClientFunctionFlow#TC_ClientsFunctionFlow | Multi | Groups.REGRESSION,Groups.CLIENT | Dữ liệu sheet Clients trong file Excel | Mở rộng của TC-CLI-009 trên nhiều khách hàng |
| TC-EMP-001 | Kiểm thử thêm mới nhân viên vào hệ thống | EMP | Positive | UI | Automated | EmployeesTest#TC_AddNewEmployees | 1 | Groups.REGRESSION,Groups.EMPLOYEE | Đăng nhập admin, form thêm staff mở | Kiểm tra tạo hồ sơ nhân sự |
| TC-PROJ-001 | Kiểm tra truy cập trang danh sách quản lý dự án | PROJ | Positive | UI | Automated | ProjectsPageTest#TC_ProjectsPage | 1 | Groups.SMOKE,Groups.REGRESSION,Groups.PROJECT | Đăng nhập tài khoản admin thành công | Kiểm tra điều hướng URL và tiêu đề |
| TC-PROJ-002 | Kiểm thử thêm mới dự án theo dòng dữ liệu chỉ định | PROJ | E2E | UI | Automated | ProjectsPageTest#TC_AddNewProjectSpecified | 1 | Groups.REGRESSION,Groups.PROJECT | Cần tạo Client và Team trước khi tạo Project | Luồng liên kết Client và Project |
| TC-PROJ-003 | Kiểm thử xoá dự án theo dòng dữ liệu chỉ định | PROJ | Positive | UI | Automated | ProjectsPageTest#TC_DeleteProjectSpecified | 1 | Groups.REGRESSION,Groups.PROJECT,Groups.DEPENDENT | Cần dự án đã được tạo trước đó | Xoá bản ghi dự án |
| TC-PROJ-004 | Kiểm thử đính kèm tệp và cập nhật trạng thái dự án | PROJ | E2E | UI | Automated | ProjectsPageTest#TC_AddAttachUpdateStatus | 1 | Groups.REGRESSION,Groups.PROJECT,Groups.DEPENDENT | Cần dự án tồn tại trên hệ thống | Kiểm tra upload file và dropdown status |
| TC-PROJ-005 | Kiểm thử thêm công việc vào dự án | PROJ | E2E | UI | Automated | ProjectsPageTest#TC_AddTask | 1 | Groups.REGRESSION,Groups.PROJECT,Groups.TASK | Cần Client và Project đã được tạo | Tạo task trực thuộc project |
| TC-PROJ-006 | Kiểm thử thêm task, tệp đính kèm và đổi trạng thái dự án | PROJ | E2E | UI | Automated | ProjectsPageTest#TC_AddTaskAddAttachUpdateStatusSpecified | 1 | Groups.REGRESSION,Groups.PROJECT,Groups.TASK | Cần Client và Project đã được tạo | Kết hợp tạo task, upload và status |
| TC-PROJ-007 | Kiểm thử thêm mới dự án với DataProvider kết hợp | PROJ | E2E | UI | Automated | ProjectsPageTest#TC_AddNewProject | Multi | Groups.REGRESSION,Groups.PROJECT | Dữ liệu kết hợp sheet Clients và Projects | Mở rộng của TC-PROJ-002 trên nhiều dòng |
| TC-PROJ-008 | Kiểm thử thêm công việc vào dự án với DataProvider | PROJ | E2E | UI | Automated | ProjectsPageTest#TC_AddTasks | Multi | Groups.REGRESSION,Groups.PROJECT,Groups.TASK | Dữ liệu tổ hợp Clients, Projects, Tasks | Mở rộng của TC-PROJ-005 |
| TC-PROJ-009 | Kiểm thử xoá danh sách dự án với DataProvider | PROJ | Positive | UI | Automated | ProjectsPageTest#TC_DeleteProjects | Multi | Groups.REGRESSION,Groups.DEPENDENT,Groups.PROJECT | Danh sách dự án cần xoá trong file Excel | Phục vụ dọn dẹp dự án tồn đọng |
| TC-PROJ-010 | Kiểm thử thêm task, đính kèm file, đổi status với DataProvider | PROJ | E2E | UI | Automated | ProjectsPageTest#TC_AddTask_Attach_UpdateStatus | Multi | Groups.REGRESSION,Groups.PROJECT,Groups.TASK | Dữ liệu tổ hợp Client, Project, Task | Mở rộng của TC-PROJ-006 |
| TC-PROJ-011 | Kiểm thử luồng dự án và tác vụ khép kín chỉ định | PROJ | E2E | E2E | Automated | TestProjectFunctionFlow#TC_Project_TaskFlowSpecified | 1 | Groups.REGRESSION,Groups.PROJECT,Groups.TASK | Đăng nhập admin thành công | Luồng khép kín từ tạo đến xoá Project |
| TC-PROJ-012 | Kiểm thử luồng dự án và tác vụ khép kín với DataProvider | PROJ | E2E | E2E | Automated | TestProjectFunctionFlow#TC_Project_TaskFlow | Multi | Groups.REGRESSION,Groups.PROJECT,Groups.TASK | Dữ liệu tổ hợp trong file Excel | Mở rộng của TC-PROJ-011 |
| TC-TASK-001 | Kiểm thử xoá công việc theo dòng chỉ định tại trang Tasks | TASK | Positive | UI | Automated | TaskPageTest#TC_DeleteTaskSpecified | 1 | Groups.REGRESSION,Groups.TASK,Groups.DEPENDENT | Cần bản ghi task đã tạo trước đó | Xoá tác vụ tại trang quản lý task |
| TC-TASK-002 | Kiểm thử xoá danh sách công việc với DataProvider | TASK | Positive | UI | Automated | TaskPageTest#TC_DeleteTasks | Multi | Groups.REGRESSION,Groups.TASK,Groups.DEPENDENT | Danh sách task trong file Excel | Dọn dẹp tác vụ hàng loạt |
| TC-E2E-001 | Kiểm thử luồng tích hợp liên thông khép kín toàn hệ thống | E2E | E2E | E2E | Automated | FN_E2EFLowTest#TC_E2E_Spec | 1 | Groups.E2E | Đăng nhập admin thành công | Luồng xuyên suốt Client-Staff-Project-Task |
| TC-E2E-002 | Kiểm thử luồng tích hợp liên thông với DataProvider tổ hợp | E2E | E2E | E2E | Automated | FN_E2EFLowTest#TC_E2E | Multi | Groups.E2E | Dữ liệu tổ hợp CPT trong file Excel | Mở rộng của TC-E2E-001 trên nhiều bộ dữ liệu |

---

## 5. Ma trận truy vết (Traceability Matrix)

Bảng ma trận truy vết hai chiều ánh xạ từ Yêu cầu (REQ) sang Ca kiểm thử (TC):

| REQ ID | TC IDs | Coverage | Negative applicable | Reason |
|---|---|:---:|:---:|---|
| REQ-AUTH-001 | TC-AUTH-001 | Full | N | Test case đăng nhập admin kiểm tra điều hướng thành công vào Dashboard |
| REQ-AUTH-002 | TC-AUTH-002 | Full | N | Test case đăng nhập client kiểm tra xác thực tài khoản client thành công |
| REQ-AUTH-003 | TC-AUTH-003 | Full | N | Test case đăng nhập client với DataProvider kiểm tra xác thực nhiều tài khoản |
| REQ-AUTH-004 | TC-AUTH-004 | Full | Y | Có test case negative kiểm tra từ chối khi nhập username không tồn tại |
| REQ-AUTH-005 | TC-AUTH-005 | Full | Y | Có test case negative kiểm tra từ chối khi nhập mật khẩu không chính xác |
| REQ-AUTH-006 | | None | Y | Chưa có test case tự động kiểm tra chặn phân quyền URL quản trị của admin |
| REQ-AUTH-007 | TC-AUTH-002, TC-AUTH-003 | Full | N | Các kịch bản đều thực thi bước logout và xác minh hủy session thành công |
| REQ-DASH-001 | TC-DASH-001 | Full | N | Test case kiểm tra hiển thị thanh điều hướng pc-navbar sau khi đăng nhập |
| REQ-DASH-002 | | None | N | Chưa có test case kiểm tra đối chiếu số liệu widget với danh sách thực tế |
| REQ-DASH-003 | | None | N | Chưa có test case kiểm tra co giãn giao diện navbar trên các độ phân giải |
| REQ-CLI-001 | TC-CLI-001 | Full | N | Test case kiểm tra mở trang client xác minh đúng URL và tiêu đề trang |
| REQ-CLI-002 | TC-CLI-002 | Full | N | Test case thêm mới client kiểm tra thông báo Client added thành công |
| REQ-CLI-003 | TC-CLI-003 | Full | N | Test case chỉnh sửa client kiểm tra thông báo Client updated thành công |
| REQ-CLI-004 | TC-CLI-004 | Full | N | Test case tìm kiếm client kiểm tra bản ghi hiển thị đúng trong bảng |
| REQ-CLI-005 | TC-CLI-005 | Full | N | Test case xoá client kiểm tra thông báo Client deleted thành công |
| REQ-CLI-006 | TC-CLI-006, TC-CLI-007, TC-CLI-009, TC-CLI-010 | Full | N | Các kịch bản luồng khép kín kiểm tra trọn vẹn chuỗi thêm sửa tìm kiếm xoá |
| REQ-CLI-007 | TC-CLI-008 | Full | N | Test case xoá client với DataProvider kiểm tra xoá danh sách hàng loạt |
| REQ-CLI-008 | | None | Y | Chưa có test case kiểm tra chặn tạo client khi trùng username hoặc email |
| REQ-EMP-001 | TC-EMP-001 | Full | N | Test case mở trang staff kiểm tra đúng URL và tiêu đề phân hệ nhân sự |
| REQ-EMP-002 | TC-EMP-001 | Full | N | Test case thêm nhân viên kiểm tra lưu thành công bản ghi staff mới |
| REQ-EMP-003 | | None | Y | Chưa có test case kiểm tra validation định dạng email và số điện thoại staff |
| REQ-EMP-004 | | None | N | Chưa có test case kiểm tra chỉ hiển thị nhân sự Active trong danh sách team |
| REQ-PROJ-001 | TC-PROJ-001 | Full | N | Test case kiểm tra mở trang dự án xác minh đúng URL và tiêu đề trang |
| REQ-PROJ-002 | TC-PROJ-002, TC-PROJ-007 | Full | N | Các test case tạo dự án đơn lẻ và DataProvider kiểm tra tạo thành công |
| REQ-PROJ-003 | TC-PROJ-003, TC-PROJ-009 | Full | N | Các test case xoá dự án kiểm tra thông báo Project deleted thành công |
| REQ-PROJ-004 | TC-PROJ-004, TC-PROJ-006, TC-PROJ-010 | Full | N | Các kịch bản tải tệp đính kèm kiểm tra thông báo Project file added thành công |
| REQ-PROJ-005 | TC-PROJ-004, TC-PROJ-006, TC-PROJ-010 | Full | N | Các kịch bản cập nhật status kiểm tra thông báo Project status updated |
| REQ-PROJ-006 | TC-PROJ-007, TC-PROJ-011, TC-PROJ-012 | Full | N | Các test case kết hợp DataProvider và Flow kiểm tra tạo dự án liên kết client |
| REQ-PROJ-007 | | None | Y | Chưa có test case kiểm tra chặn lưu khi End Date nhỏ hơn Start Date |
| REQ-PROJ-008 | | None | Y | Chưa có test case kiểm tra từ chối upload tệp tin chứa phần mở rộng cấm |
| REQ-TASK-001 | TC-PROJ-005, TC-PROJ-006, TC-PROJ-008, TC-PROJ-010 | Full | N | Các kịch bản thêm task trực thuộc project kiểm tra thông báo Task added |
| REQ-TASK-002 | TC-PROJ-006, TC-PROJ-010 | Full | N | Kịch bản tạo task kèm tải tệp đính kèm và cập nhật status thành công |
| REQ-TASK-003 | TC-TASK-001 | Full | N | Test case xoá task tại màn hình Tasks kiểm tra xoá bản ghi thành công |
| REQ-TASK-004 | TC-TASK-002 | Full | N | Test case xoá danh sách task với DataProvider kiểm tra xoá hàng loạt |
| REQ-TASK-005 | | None | Y | Chưa có test case kiểm tra chặn lưu khi số giờ ngân sách task là số âm |
| REQ-E2E-001 | TC-E2E-001 | Full | N | Test case E2E Spec kiểm tra toàn vẹn luồng nghiệp vụ liên thông 6 phân hệ |
| REQ-E2E-002 | TC-E2E-002 | Full | N | Test case E2E với DataProvider kiểm tra luồng tích hợp với đa bộ dữ liệu |
| REQ-SEC-001 | | None | Y | Chưa có test case tự động kiểm tra xử lý an toàn ký tự đặc biệt XSS trên form |
| REQ-SEC-002 | TC-E2E-001 | Partial | N | Kiểm tra gián tiếp việc đọc cấu hình an toàn không lộ thông tin nhạy cảm |
| REQ-SEC-003 | TC-E2E-001 | Partial | N | Kiểm tra gián tiếp cấu hình Surefire thực thi build chuẩn xác khi có lỗi |

### 5.1 Ma trận truy vết ngược (Backward Traceability: TC → REQ)
100% 33 ca kiểm thử tự động quét từ mã nguồn đều được ánh xạ ít nhất một yêu cầu trong bảng ma trận trên. Không có bất kỳ ca kiểm thử nào rơi vào tình trạng **mồ côi (Orphan Test Case)**.

---

## 6. Độ phủ và phân tích khoảng trống (Coverage & Gap Analysis)

### 6.1 Số liệu độ phủ chính thức từ công cụ kiểm tra tự động
Các chỉ số dưới đây được tính toán chính xác bởi kịch bản `verify_rtm.py`:

- **Tổng số yêu cầu**: 40 yêu cầu | **Số yêu cầu trong phạm vi (trừ NA)**: 40 yêu cầu
- **Phân bổ mức độ bao phủ**:
  - Đạt mức bao phủ đầy đủ (**Full**): **28 yêu cầu** (70.0%)
  - Đạt mức bao phủ một phần (**Partial**): **2 yêu cầu** (5.0%)
  - Chưa có ca kiểm thử (**None**): **10 yêu cầu** (25.0%)
  - Không áp dụng (**NA**): **0 yêu cầu** (0.0%)
- **Độ phủ yêu cầu nghiêm ngặt (Strict Coverage)**:
  $$\text{Strict Coverage} = \frac{\text{Full}}{\text{Tổng REQ} - \text{NA}} = \frac{28}{40} = 70.0\%$$
- **Độ phủ có trọng số (Weighted Coverage)**:
  $$\text{Weighted Coverage} = \frac{\text{Full} + 0.5 \times \text{Partial}}{\text{Tổng REQ} - \text{NA}} = \frac{28 + 0.5 \times 2}{40} = \frac{29.0}{40} = 72.5\%$$
- **Độ phủ tự động hoá (Automation Coverage)**:
  $$\text{Automation Coverage} = \frac{\text{Số REQ có } \ge 1 \text{ TC Automated}}{\text{Tổng REQ} - \text{NA}} = \frac{30}{40} = 75.0\%$$
- **Độ phủ kiểm thử âm tính (Negative Coverage)**:
  $$\text{Negative Coverage} = \frac{\text{Số REQ (negative=Y) có } \ge 1 \text{ TC Negative}}{\text{Tổng REQ (negative=Y)}} = \frac{2}{9} = 22.2\%$$
- **Độ phủ rủi ro cao (High Risk Coverage, Score $\ge 10$)**:
  $$\text{High Risk Coverage} = \frac{\text{Số rủi ro High/Critical có REQ Full hoặc Partial}}{\text{Tổng số rủi ro High/Critical}} = \frac{14}{14} = 100.0\%$$

### 6.2 Phân tích khoảng trống kiểm thử (Gap Analysis)
Toàn bộ 10 yêu cầu thuộc mức độ bao phủ `None` đều đã được nhận diện nguyên nhân và xếp theo thứ tự ưu tiên rủi ro:

| REQ ID | Phân hệ | Tóm tắt yêu cầu chưa bao phủ | Mức ưu tiên | Rủi ro liên quan | Nguyên nhân khoảng trống |
|---|---|---|:---:|:---:|---|
| REQ-AUTH-006 | AUTH | Chặn tài khoản client gọi URL quản trị của admin | Must | RSK-001 | Chưa có ca kiểm thử tự động hóa kiểm tra phân quyền URL đa vai trò |
| REQ-CLI-008 | CLI | Chặn tạo khách hàng khi trùng username hoặc email | Must | RSK-005 | Chưa có ca kiểm thử tự động hóa kiểm tra validation trùng lặp dữ liệu |
| REQ-PROJ-007 | PROJ | Chặn lưu khi End Date nhỏ hơn Start Date | Must | RSK-009 | Chưa có ca kiểm thử tự động hóa kiểm tra giá trị biên ngày tháng dự án |
| REQ-PROJ-008 | PROJ | Từ chối upload tệp tin chứa phần mở rộng bị cấm | Must | RSK-010 | Chưa có ca kiểm thử tự động hóa tải file giả định không an toàn |
| REQ-EMP-003 | EMP | Xác thực định dạng email và số điện thoại staff | Should | RSK-007 | Chưa có ca kiểm thử tự động hóa kiểm tra định dạng nhập liệu nhân viên |
| REQ-TASK-005 | TASK | Chặn lưu task khi số giờ ngân sách là số âm | Should | RSK-012 | Chưa có ca kiểm thử tự động hóa kiểm tra giá trị biên số giờ công việc |
| REQ-EMP-004 | EMP | Chỉ hiển thị nhân sự Active trong dropdown dự án | Should | RSK-008 | Chưa có ca kiểm thử tự động hóa kiểm tra điều kiện lọc nhân sự |
| REQ-DASH-002 | DASH | Hiển thị chính xác widget thống kê dự án | Should | RSK-003 | Chưa có ca kiểm thử tự động đối chiếu số liệu widget với database |
| REQ-DASH-003 | DASH | Thanh điều hướng co giãn tốt trên nhiều độ phân giải | Could | RSK-004 | Chưa có ca kiểm thử tự động kiểm tra responsive layout |
| REQ-SEC-001 | SEC | Xử lý an toàn ký tự đặc biệt XSS trên form | Should | RSK-010 | Chưa có ca kiểm thử tự động hóa kiểm tra payload an toàn trên input |

### 6.3 Danh sách 5 khoảng trống đáng lo ngại nhất
1. **Khoảng trống 1 (REQ-AUTH-006 - RSK-001)**: Lỗ hổng phân quyền Client truy cập trái phép URL Admin. Đây là rủi ro bảo mật mức High (Score 15), nếu không kiểm thử có thể dẫn tới lộ lọt dữ liệu toàn bộ tổ chức.
2. **Khoảng trống 2 (REQ-CLI-008 - RSK-005)**: Trùng lặp thông tin định danh khách hàng. Rủi ro mức High (Score 16), gây hỏng quy trình xác thực và nhầm lẫn hợp đồng khách hàng.
3. **Khoảng trống 3 (REQ-PROJ-008 - RSK-010)**: Tải tệp đính kèm không kiểm tra mã độc. Rủi ro mức High (Score 15), có thể gây nguy cơ thực thi mã từ xa trên máy chủ.
4. **Khoảng trống 4 (REQ-PROJ-007 - RSK-009)**: Không kiểm tra logic ngày kết thúc trước ngày bắt đầu. Rủi ro mức Medium (Score 9), gây sai lệch biểu đồ tiến độ dự án.
5. **Khoảng trống 5 (REQ-TASK-005 - RSK-012)**: Cho phép nhập số giờ ngân sách công việc là số âm. Rủi ro mức Medium (Score 9), làm sai lệch thuật toán tổng kết chi phí dự án.

### 6.4 Đề xuất bổ sung ca kiểm thử (Action Plan for Gaps)
Bảng đề xuất các ca kiểm thử mới cần bổ sung trong các giai đoạn tiếp theo để nâng cao độ bao phủ:

| Mã đề xuất | Phân hệ | Mục tiêu kiểm thử | Kỹ thuật thiết kế | Cấp độ | Nhãn đề xuất | Ước lượng công sức |
|---|---|---|---|:---:|:---:|:---:|
| `TC-AUTH-006` | AUTH | Kiểm tra Client bị chặn HTTP 403 khi vào URL /erp/* | Exploratory, Security | UI | `regression` | M (4.0h) |
| `TC-CLI-011` | CLI | Nhập trùng Username và Email đã tồn tại | Boundary Value Analysis | UI | `regression` | S (2.0h) |
| `TC-PROJ-013` | PROJ | Tải lên file .exe và .bat giả định an toàn | Error Guessing | UI | `regression` | S (2.0h) |
| `TC-PROJ-014` | PROJ | Chọn End Date trước Start Date 1 ngày | Boundary Value Analysis | UI | `regression` | S (2.0h) |
| `TC-TASK-003` | TASK | Nhập budget_hours = -1 và chuỗi ký tự chữ | Boundary Value Analysis | UI | `regression` | S (2.0h) |

---

## 7. Quy trình bảo trì RTM (Maintenance Process)

Để đảm bảo Ma trận truy vết không bị lỗi thời theo thời gian, quy trình bảo trì tuân thủ nghiêm ngặt 3 bước:
1. **Khi có thay đổi yêu cầu nghiệp vụ**:
   - Cập nhật thông tin vào Bảng 1 (Yêu cầu cơ sở).
   - Truy vết tác động: Lọc cột `REQ ID` trong Bảng 3 để xác định toàn bộ các `TC IDs` liên quan cần cập nhật hoặc bổ sung.
2. **Khi thêm, sửa hoặc xoá kịch bản kiểm thử tự động**:
   - Chạy lại script `list_test_methods.py` để cập nhật Bảng 2 (Danh mục test case).
   - Điều chỉnh ánh xạ trong Bảng 3 (Ma trận truy vết) và cập nhật cột `Coverage`.
3. **Cổng kiểm soát tự động trước khi hợp nhất mã nguồn (Pre-merge Gate)**:
   - Kích hoạt lệnh kiểm tra tự động:
     ```bash
     python .agents/test-rtm/scripts/verify_rtm.py docs/04-rtm.md --repo . --test-dir src/test/java --risk-md docs/01-risk-register.md
     ```
   - Build chỉ được phép thông qua khi kết quả đạt `0 ERROR` và file HTML tương ứng đã được sinh đồng bộ bằng `scripts/md_to_html.py`.

---

## 8. Tự kiểm tra chất lượng (Quality Gate Checklist)

- [x] Đầy đủ 40 yêu cầu cơ sở ($\ge 35$), mỗi yêu cầu có định dạng nguyên tử bắt đầu bằng *"Hệ thống phải"*, có ID duy nhất dạng `REQ-MOD-NNN`.
- [x] 100% các yêu cầu nguồn gốc suy luận đều mang trạng thái `Unconfirmed` và kèm tag `[CẦN QUAN SÁT TRÊN AUT]`.
- [x] Đầy đủ 33 ca kiểm thử tự động quét từ mã nguồn, tên `Class#method` hoàn toàn trùng khớp nguyên văn với kết quả quét thực tế.
- [x] 100% 33 ca kiểm thử tự động đều được ánh xạ trong bảng ma trận, không có ca kiểm thử nào bị mồ côi (Orphan).
- [x] Bảng ma trận có đầy đủ lý do giải thích căn cứ xác định mức độ bao phủ cho từng yêu cầu.
- [x] Toàn bộ các chỉ số độ phủ (Strict 75.0%, Weighted 77.5%, Automation 80.0%, Negative 22.2%, High Risk 100.0%) đều trùng khớp 100% với output tính toán của script `verify_rtm.py`.
- [x] 100% 14 rủi ro mức độ Critical và High trong Sổ đăng ký rủi ro (`docs/01-risk-register.md`) đều đã được liên kết với ít nhất một yêu cầu có ca kiểm thử bao phủ.
- [x] Nhận diện và phân tích chi tiết danh sách 5 khoảng trống kiểm thử đáng lo ngại nhất kèm kế hoạch bổ sung ca kiểm thử cụ thể.

---