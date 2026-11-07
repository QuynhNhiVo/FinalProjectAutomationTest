# Ghi chép quan sát hệ thống (AUT observations)

> Tài liệu làm việc ghi nhận các hành vi, ràng buộc và lỗi phát hiện **thực tế trên hệ thống AUT (Anh Tester HRM - https://hrm.anhtester.com/)**.
> Căn cứ theo kết quả đối soát thực tế của kỹ sư kiểm thử (QA Tester Võ Quỳnh Nhi), tài liệu `docs/ui-inventory.md` và mã nguồn kiểm thử tự động tại `src/test/java/`.

---

## 1. Ràng buộc và hành vi đã xác nhận thực tế (Confirmed Behaviors)

| Màn hình | Trường/Thao tác | Hành vi quan sát được trên AUT (Confirmed Behavior) | Tình trạng kiểm tra | Ghi chú nghiệp vụ |
|---|---|---|---|---|
| ManageClientsPage / EmployeesPage | Các trường bắt buộc (`First Name`, `Last Name`, `Email`, `Username`, `Password`) | Khi để trống bất kỳ trường bắt buộc nào, hệ thống hiển thị thông báo lỗi nguyên văn: `The "<tên trường>" field is required.` (Ví dụ: `The first name field is required.`, `The email field is required.`). Riêng dropdown Gender không bắt buộc. | Đã xác nhận trên AUT | Chuẩn validation bắt buộc |
| ManageClientsPage / EmployeesPage | Các trường nhập liệu văn bản | Hệ thống không chấp nhận chuỗi chỉ chứa ký tự khoảng trắng (whitespace) và yêu cầu người dùng phải nhập liệu hợp lệ. | Đã xác nhận trên AUT | Chặn nhập khoảng trắng |
| ManageClientsPage / LoginPage | Mật khẩu (`inputPassword`, `ipPassword`) | Độ dài mật khẩu bắt buộc tối thiểu là 6 ký tự. Nhập dưới 6 ký tự sẽ bị chặn hoặc báo lỗi không hợp lệ. | Đã xác nhận trên AUT | Ràng buộc bảo mật mật khẩu |
| ManageClientsPage / EmployeesPage | First Name & Username | **Hiện tại hệ thống không giới hạn độ dài ký tự tối đa** (chấp nhận chuỗi dài không giới hạn). | Đã xác nhận trên AUT | Thiếu validation độ dài biên |
| ManageClientsPage / EmployeesPage | Số điện thoại (`inputContactNumber`, `inputContact`) | Yêu cầu nhập đúng 10 chữ số (không cho phép dưới 10 số hoặc trên 10 số, không chứa ký tự chữ). | Đã xác nhận trên AUT | Ràng buộc định dạng 10 số |
| ManageClientsPage / EmployeesPage | Địa chỉ Email (`inputEmail`) | Hệ thống có cơ chế kiểm tra **định dạng Email chuẩn** (phải có `@` và tên miền hợp lệ). | Đã xác nhận trên AUT | Chuẩn định dạng RFC Email |
| ManageClientsPage / EmployeesPage | Trùng Username hoặc Email (`REQ-CLI-008`, `REQ-EMP-003`) | Khi nhập Username hoặc Email đã tồn tại trong hệ thống, thông báo lỗi nguyên văn hiển thị: `This <trường lỗi> already exist. Please choose another <trường lỗi>.` | Đã xác nhận trên AUT | Chặn trùng lặp định danh |
| ManageClientsPage / ProjectsPage | Tệp tin đính kèm (`inputAttachment`, `uploadFiles`) | Hệ thống có whitelist định dạng tệp tin cho phép, thông báo nguyên văn: `Upload files only: gif,png,jpg,jpeg`. Chặn tải lên các tệp `.exe`, `.bat`, tài liệu lạ. | Đã xác nhận trên AUT | Whitelist định dạng tệp an toàn |
| ManageClientsPage | Nút Save (Submit form) | **Bấm Save liên tiếp (Double Click) không tạo ra bản ghi thứ hai**, hệ thống kiểm soát chống trùng lặp submit tốt. | Đã xác nhận trên AUT | Kiểm soát giao dịch an toàn |
| CommonPage / Admin URLs | Client gọi trực tiếp URL Admin (`/erp/clients-list`, `/erp/staff-list`) | Khi tài khoản vai trò Client gọi trực tiếp các đường dẫn quản trị của Admin, hệ thống từ chối quyền truy cập và trả về: `Error Message: Unauthorized Access`. | Đã xác nhận trên AUT | Phân quyền bảo mật RBAC |
| LoginPage / ManageClientsPage | Trạng thái tài khoản Client bị `Banned` | Khi Admin chuyển trạng thái tài khoản Client sang `Banned`, Client cố gắng đăng nhập tại màn hình Login sẽ nhận thông báo lỗi: `Invalid Login Credentials.`. | Đã xác nhận trên AUT | Vô hiệu hoá tài khoản |
| ManageClientsPage | Xoá Client đang có Project liên kết (`REQ-CLI-005`, `RSK-006`) | Hệ thống thực hiện cơ chế **Xoá toàn bộ liên quan (Cascade Delete)**, xoá thành công Client và đồng thời tự động xoá sạch các Project/Task trực thuộc khách hàng đó. | Đã xác nhận trên AUT | Hành vi xóa cascade thực tế |
| ProjectsPage | Logic ngày dự án: End Date < Start Date (`REQ-PROJ-007`) | **Hiện tại hệ thống web KHÔNG ngăn chặn lỗi này** (cho phép lưu dự án có ngày kết thúc trước ngày bắt đầu), tuy nhiên theo quy chuẩn nghiệp vụ thì **KHÔNG ĐƯỢC PHÉP**. | Phát hiện lỗi (Defect) trên AUT | Lỗi thiếu ràng buộc thời gian |
| ProjectsPage / TasksPage | Số giờ ngân sách âm: Budget Hours < 0 (`REQ-TASK-005`) | **Hiện tại hệ thống web KHÔNG ngăn chặn lỗi này** (cho phép lưu số giờ làm việc là số âm), tuy nhiên theo quy chuẩn nghiệp vụ thì **KHÔNG ĐƯỢC PHÉP**. | Phát hiện lỗi (Defect) trên AUT | Lỗi thiếu ràng buộc số âm |

---

## 2. Điểm cần kiểm tra sâu hơn (Exploratory Testing Checklist)

| Màn hình | Thao tác | Mục tiêu kiểm tra | Ghi chú |
|---|---|---|---|
| ManageClientsPage / Form nhập liệu | Ký tự XSS Script (`REQ-SEC-001`) | Kiểm tra chuỗi `<script>alert(1)</script>` khi hiển thị lại trên bảng danh sách xem có bị thực thi mã độc hay được encode HTML an toàn. | Cần chạy kiểm thử thực tế để đánh giá lỗ hổng |
