# Test Plan – Kế hoạch kiểm thử chi tiết

## 1. Document Control

### 1.1 Thông tin tài liệu
- **Tên dự án**: Final Project Automation Test – Anh Tester HRM
- **Hệ thống được kiểm thử (AUT)**: Anh Tester HRM System (https://hrm.anhtester.com/)
- **Mã tài liệu**: PLN-HRM-03
- **Phiên bản**: v0.1
- **Trạng thái**: Draft
- **Tác giả**: Võ Quỳnh Nhi
- **Chu kỳ kiểm thử**: Chu kỳ kiểm thử hoàn thiện đồ án tốt nghiệp (10 tuần: từ 2026-11-02 đến 2027-01-10)
- **Ngày lập kế hoạch**: 2026-11-02
- **Ngày phê duyệt**: [CẦN XÁC NHẬN: Ngày ký duyệt chính thức]

### 1.2 Lịch sử thay đổi
| Phiên bản | Ngày cập nhật | Tác giả | Tóm tắt thay đổi |
|---|---|---|---|
| v0.1 | 2026-11-02 | Võ Quỳnh Nhi | Khởi tạo Kế hoạch kiểm thử chi tiết (Test Plan) cho chu kỳ 10 tuần, tích hợp bảng ước lượng WBS ba điểm |

### 1.3 Tài liệu tham chiếu
- `docs/project-context.md`: Bối cảnh dự án kiểm thử tự động HRM
- `docs/01-risk-register.md`: Sổ đăng ký rủi ro kiểm thử (Risk Register)
- `docs/02-test-strategy.md`: Chiến lược kiểm thử tự động (Test Strategy)
- Tiêu chuẩn ISO/IEC/IEEE 29119-3 (Level Test Plan) & ISTQB Advanced Test Manager

---

## 2. Giới thiệu (Introduction)

### 2.1 Mục đích kế hoạch
Tài liệu Kế hoạch kiểm thử (Test Plan) này cụ thể hóa Chiến lược kiểm thử (`docs/02-test-strategy.md`) cho chu kỳ thực thi đồ án tốt nghiệp kéo dài **10 tuần (từ ngày 2026-11-02 đến ngày 2027-01-10)**. Kế hoạch xác định rõ ràng:
- Các hạng mục phần mềm và tính năng cụ thể cần kiểm thử trong chu kỳ.
- Phân rã công việc chi tiết (WBS) theo phương pháp ước lượng ba điểm (PERT O/M/P).
- Lịch trình thực hiện theo tuần, đường găng dự án (Critical Path) và các mốc bàn giao then chốt (`MS-01` đến `MS-08`).
- Tiêu chí bắt đầu, tiêu chí hoàn thành, cơ chế quản lý lỗi và biện pháp dự phòng rủi ro kế hoạch (`TP-R01` đến `TP-R05`).

### 2.2 Phạm vi chu kỳ và quan hệ với Test Strategy
- **Kế thừa từ Chiến lược kiểm thử**:
  - Kế thừa toàn bộ 10 quyết định kiến trúc (`STR-D01` đến `STR-D10`), đặc biệt là việc duy trì nền tảng Selenium Java TestNG (`STR-D01`), áp dụng Page Object Model kết hợp WebUI Keyword Wrapper (`STR-D02`), loại bỏ hoàn toàn sleep tĩnh sang WebDriverWait (`STR-D03`) và áp dụng chính sách dữ liệu nguyên tử (`STR-D04`).
  - Kế thừa hệ thống phân loại nhãn mức chạy: `smoke`, `regression`, `e2e` và các cờ hành vi `negative`, `dependent`.
  - Kế thừa các chỉ số đo lường KPI và ma trận phân loại lỗi Severity × Priority.
- **Phần điều chỉnh / Cụ thể hóa trong chu kỳ này**:
  - Phân bổ nguồn lực cụ thể: 1 nhân sự (Võ Quỳnh Nhi) với định mức **20 giờ/tuần**, tổng quỹ năng lực đạt **200.00 giờ** trong 10 tuần.
  - Phân bổ công việc thành 20 gói đầu việc (`T01` đến `T20`) với tổng công sức ước tính **152.00 giờ** cộng **20% dự phòng (30.40 giờ)** thành **182.40 giờ** (hoàn toàn khả thi trong ngân sách 200 giờ).
  - Không áp dụng ngày nghỉ cố định; thời gian làm việc được phân bổ linh hoạt theo tiến độ các mốc bàn giao.

---

## 3. Hạng mục kiểm thử (Test Items)

### 3.1 Hệ thống phần mềm mục tiêu (AUT)
- **Tên hệ thống**: Anh Tester HRM System
- **Địa chỉ truy cập**: `https://hrm.anhtester.com/`
- **Phiên bản hệ thống**: Bản phát hành Demo Web ERP v1.0
- **Ngày chốt phiên bản kiểm thử**: 2026-11-02

### 3.2 Bộ kịch bản kiểm thử tự động (Automation Test Suite)
- **Kho lưu trữ mã nguồn**: `d:\Dev\4_Projects\FinalProjectAutomationTest`
- **Nhánh thực thi**: `main`
- **Commit chốt đầu chu kỳ**: Commit khởi tạo chu kỳ ngày 2026-11-02
- **Thư mục kịch bản**: `src/test/java/projectHRM/testcases/`
- **Thư mục mô hình trang**: `src/test/java/projectHRM/pages/`
- **Thư mục cấu hình & suite XML**: `src/test/resources/suites/`

### 3.3 Tài liệu liên quan
- Bảng dữ liệu kiểm thử Excel: `src/test/resources/testdata/LoginHRM.xlsx`
- File cấu hình tham số: `src/test/resources/configs/Configs.properties`

---

## 4. Tính năng cần kiểm thử (Features to be Tested)

Bảng dưới đây quy định toàn bộ các tính năng thuộc phạm vi kiểm thử của chu kỳ 10 tuần, gắn liền với mức độ ưu tiên rủi ro trong Sổ đăng ký rủi ro (`docs/01-risk-register.md`):

| Phân hệ / Tính năng | Mức ưu tiên | Rủi ro liên quan | Loại kiểm thử | Nhãn mức chạy | Kỹ thuật thiết kế áp dụng | Ghi chú vận hành |
|---|:---:|:---:|---|:---:|---|---|
| `auth` - Đăng nhập Quản trị viên (Admin) | P1 - Cao | RSK-002, RSK-023 | Smoke, Functional | `smoke`, `regression` | Phân vùng tương đương, BVA | Luồng then chốt kiểm tra sức khỏe đầu chu kỳ |
| `auth` - Xác thực phân quyền chéo Client/Admin | P1 - Cao | RSK-001 | Security, Functional | `regression` | Kiểm thử biên an toàn, Exploratory | Chặn tài khoản Client gọi URL quản trị /erp/* |
| `auth` - Đăng nhập Client và Validation lỗi | P2 - Vừa | RSK-002 | Functional, Negative | `smoke`, `regression` | Error Guessing, Phân vùng tương đương | Kiểm tra thông báo khi sai user/password |
| `dashboard` - Hiển thị widget và thanh điều hướng | P3 - Thấp | RSK-003, RSK-004 | GUI, Usability | `regression` | Kiểm thử thăm dò (Exploratory) | Loại bỏ lệnh sleep(9), kiểm tra hiển thị navbar |
| `client` - Thêm mới khách hàng (Add Client) | P1 - Cao | RSK-005, RSK-017 | Functional | `regression` | Phân tích giá trị biên, EP | Chuyển sang sinh dữ liệu timestamp duy nhất |
| `client` - Cập nhật và Xác minh thông tin Client | P2 - Vừa | RSK-005 | Functional | `regression` | Bảng quyết định, Data-Driven | Kiểm tra chỉnh sửa thông tin liên lạc |
| `client` - Xoá khách hàng và Ràng buộc toàn vẹn | P1 - Cao | RSK-006 | Functional | `smoke`, `regression` | State Transition, Error Guessing | Xoá bản ghi test và dọn dẹp dữ liệu rác |
| `employee` - Thêm mới nhân sự (Staff) | P2 - Vừa | RSK-007 | Functional | `regression` | Phân vùng tương đương, BVA | Kiểm tra định dạng số điện thoại và email |
| `employee` - Phân công nhân sự vào dự án | P2 - Vừa | RSK-008 | Functional | `regression` | Bảng quyết định (Decision Table) | Chỉ nhân sự Active mới hiển thị trong dropdown |
| `project` - Thêm mới dự án (Add Project) | P1 - Cao | RSK-009, RSK-017 | Functional | `regression` | Phân tích giá trị biên, EP | Kiểm tra logic Start Date và End Date hợp lệ |
| `project` - Đính kèm tệp tin tài liệu (Upload) | P1 - Cao | RSK-010, RSK-021 | Security, Functional | `regression` | Error Guessing, Portability | Chuyển sang đường dẫn tương đối SystemHelpers |
| `project` - Cập nhật trạng thái và Xoá dự án | P2 - Vừa | RSK-017, RSK-026 | Functional | `regression` | State Transition | Cập nhật tiến độ dự án và dọn dẹp sau test |
| `task` - Thêm công việc trực thuộc dự án | P2 - Vừa | RSK-011, RSK-012 | Functional | `regression` | BVA, Equivalence Partitioning | Kiểm tra số giờ ngân sách không được là số âm |
| `task` - Chuyển trạng thái và Xoá tác vụ | P2 - Vừa | RSK-011, RSK-026 | Functional | `regression` | Bảng chuyển trạng thái | Kiểm tra task Closed không cho phép sửa giờ |
| `e2e` - Luồng tích hợp liên thông khép kín | P1 - Cao | RSK-017, RSK-018 | Integration Flow | `e2e` | Use Case Testing, End-to-End | Tạo Client -> Employee -> Project -> Task -> Xoá |

---

## 5. Tính năng không kiểm thử (Features Not to be Tested)

Bảng dưới đây liệt kê các tính năng nằm ngoài phạm vi thực thi của chu kỳ 10 tuần, kèm lý do kỹ thuật và rủi ro chấp nhận:

| Phân hệ / Tính năng ngoài phạm vi | Lý do không kiểm thử trong chu kỳ này | Rủi ro chấp nhận và giải pháp kiểm soát | Mã rủi ro liên quan |
|---|---|---|:---:|
| Phân hệ Tiền lương (Payroll) & Thưởng | Chưa nằm trong mục tiêu tự động hóa cốt lõi của đồ án; tập trung vào chuỗi nghiệp vụ Client - Project - Task | Chấp nhận không bao phủ tự động; kiểm tra thủ công nhanh nếu có yêu cầu đối soát | RSK-015 |
| Phân hệ Điểm danh (Attendance) | Yêu cầu cấu hình ca làm việc và thiết bị chấm công phức tạp, tốn thời gian thiết lập ban đầu | Hoãn sang chu kỳ phát triển tiếp theo sau khi bảo vệ đồ án | RSK-015 |
| Kiểm thử hiệu năng chịu tải cao (Load Testing) | AUT là hệ thống demo công khai dùng chung cho cộng đồng; nghiêm cấm gửi tải nặng làm nghẽn máy chủ | Chấp nhận không đo ngưỡng chịu tải tối đa; chỉ đo thời gian tải trang cơ bản qua Selenium | RSK-023 |
| Kiểm thử xâm nhập bảo mật (Pentest sâu) | Tránh nguy cơ làm gián đoạn hạ tầng máy chủ demo công khai | Chỉ thực hiện kiểm tra an toàn form nhập liệu ở mức độ không phá hoại | RSK-010 |
| Kiểm thử trên thiết bị di động thật | Giới hạn phần cứng và mục tiêu đồ án chỉ tập trung vào ứng dụng Web trên Desktop | Chấp nhận kiểm tra giao diện cơ bản trên các độ phân giải màn hình máy tính | RSK-004 |

---

## 6. Cách tiếp cận của chu kỳ (Cycle Approach)

### 6.1 Chiến lược thực thi theo Suite kiểm thử
Kế hoạch thực thi chu kỳ tổ chức thành 3 suite kiểm thử chuyên biệt, chạy theo tần suất và điều kiện kích hoạt rõ ràng:

| Tên Suite | Nhãn mức chạy | Thời điểm kích hoạt | Số lượng ca kiểm thử | Thời lượng ước tính [CẦN ĐO] | Hệ thống / Người thực thi | Điều kiện tiên quyết để chạy |
|---|:---:|---|:---:|:---:|---|---|
| **Smoke Suite** (`smoke.xml`) | `smoke` | Đầu mỗi ngày làm việc hoặc trước mỗi đợt chạy hồi quy | 6 kịch bản cốt lõi | [CẦN ĐO: Dự kiến <= 3 phút] | Automation Tester (chạy qua CLI/IDE) | Môi trường web online, Pre-flight Healthcheck pass |
| **Regression Suite** (`regression.xml`) | `regression` | Định kỳ 2 lần/tuần (thứ Tư và thứ Bảy) | 22 kịch bản toàn diện | [CẦN ĐO: Dự kiến <= 15 phút] | Automation Tester (chạy Maven CLI) | Smoke Suite đã pass 100%, file Excel dữ liệu sẵn sàng |
| **E2E Integration Suite** (`e2e.xml`) | `e2e` | Cuối mỗi tuần (Chủ nhật) hoặc sau các mốc hoàn thiện | 2 kịch bản luồng dài | [CẦN ĐO: Dự kiến <= 8 phút] | Automation Tester (chạy Maven CLI) | Toàn bộ các phân hệ đơn lẻ đã vượt qua Regression |

### 6.2 Biện pháp bổ sung và điều chỉnh trong chu kỳ
- Triển khai kịch bản kiểm tra sức khỏe đầu chu kỳ (Pre-flight Healthcheck) để xác thực trạng thái hoạt động của website và quyền tài khoản `admin_example` trước khi kích hoạt suite chính (`STR-D07`).
- Chuẩn hóa toàn bộ cơ chế chờ: thay thế triệt để các lệnh `sleep()` tĩnh bằng `WebDriverWait` trong các lớp `DashboardPage`, `ManageClientsPage`, `ProjectsPage` (`STR-D03`).
- Áp dụng kỹ thuật tiền tố `AUTOTEST_` kết hợp timestamp để đảm bảo tính độc lập dữ liệu tuyệt đối (`STR-D04`).

---

## 7. Tiêu chí đạt/không đạt (Pass/Fail Criteria)

### 7.1 Tiêu chí Đạt (Pass Criteria) cho từng ca kiểm thử đơn lẻ
- 100% các bước thao tác giao diện thực hiện thành công không phát sinh ngoại lệ không mong muốn (`NoSuchElementException`, `TimeoutException`, `StaleElementReferenceException`).
- Toàn bộ các điểm xác minh kết quả (`Assert.assertEquals`, `softAssert`) thỏa mãn điều kiện mong đợi.
- Dữ liệu tạo mới hiển thị chính xác trên giao diện và tìm kiếm thành công trong bảng dữ liệu.
- Kịch bản hoàn thành bước dọn dẹp dữ liệu (xóa bản ghi vừa tạo) mà không làm ảnh hưởng bản ghi khác.

### 7.2 Tiêu chí Không đạt (Fail Criteria)
- Phát sinh lỗi chức năng phần mềm: giao diện hiển thị thông báo lỗi hệ thống (HTTP 500), không lưu được bản ghi hợp lệ hoặc lưu sai thông tin.
- Phát sinh lỗi vi phạm phân quyền: tài khoản Client truy cập thành công vào trang quản trị Admin (`RSK-001`).
- Kịch bản kiểm thử bị dừng đột ngột (Crash) do lỗi framework hoặc lỗi locator bị gãy.
- Thời gian tải trang vượt quá mức trần `PAGE_LOAD_TIMEOUT = 25s` dẫn tới TimeoutException.

---

## 8. Tiêu chí đình chỉ và tiếp tục (Suspension and Resumption Criteria)

### 8.1 Điều kiện đình chỉ thực thi kiểm thử
1. Trang web `https://hrm.anhtester.com/` bị mất kết nối hoặc trả về lỗi máy chủ (HTTP 502, 503) kéo dài quá 1 giờ liên tục.
2. Tài khoản quản trị mặc định `admin_example` bị người ngoài đổi mật khẩu khiến kịch bản Pre-flight Healthcheck thất bại (`RSK-023`).
3. Tỷ lệ thất bại của Smoke Suite vượt quá 50%, cho thấy môi trường demo đang bị can thiệp dữ liệu nghiêm trọng.
4. Phát sinh lỗi biên dịch mã nguồn nghiêm trọng trên Maven sau khi cập nhật thư viện.

### 8.2 Hành động cụ thể khi đình chỉ
- Lập tức dừng thực thi các suite kiểm thử hồi quy lớn để tránh ghi thêm log rác và tốn thời gian vô ích.
- Gửi thông báo đến giảng viên hướng dẫn hoặc quản trị viên trang web hỗ trợ reset dữ liệu/mật khẩu tài khoản demo.
- Ghi nhận nhật ký sự cố đình chỉ kèm mốc thời gian và nguyên nhân chi tiết.

### 8.3 Điều kiện để tiếp tục kiểm thử (Resumption)
1. Quản trị viên trang web xác nhận dịch vụ đã được khôi phục trạng thái vận hành ổn định.
2. Tài khoản quản trị `admin_example` đăng nhập thành công trở lại qua kịch bản Healthcheck.
3. Chạy thử nghiệm thành công 1 kịch bản khói độc lập trước khi kích hoạt lại toàn bộ suite kiểm thử bị tạm dừng.

---

## 9. Tiêu chí vào và ra (Entry and Exit Criteria)

Bảng dưới đây quy định các tiêu chí định lượng bắt buộc để chuyển giai đoạn trong chu kỳ kiểm thử. Mọi ngưỡng đều có căn cứ kỹ thuật và gắn liền với rủi ro:

| Nhóm tiêu chí | Nội dung tiêu chí kiểm tra | Phương pháp kiểm tra xác nhận | Ngưỡng mục tiêu [ĐỀ XUẤT] | Cơ sở kỹ thuật gắn với rủi ro |
|---|---|---|---|---|
| **Tiêu chí vào (Entry)** | Môi trường web AUT hoạt động bình thường | Thực thi kịch bản Pre-flight Healthcheck | [ĐỀ XUẤT: 100% Pass] | Đảm bảo server sẵn sàng trước khi chạy suite (RSK-023) |
| **Tiêu chí vào (Entry)** | Mã nguồn framework biên dịch thành công | Chạy lệnh `mvn clean compile` | [ĐỀ XUẤT: 0 lỗi biên dịch] | Tránh crash khi thực thi kịch bản tự động |
| **Tiêu chí vào (Entry)** | Dữ liệu kiểm thử mẫu đã sẵn sàng | Kiểm tra file Excel `LoginHRM.xlsx` | [ĐỀ XUẤT: Đủ 5 sheet dữ liệu] | Phục vụ Data-Driven Testing các phân hệ |
| **Tiêu chí vào (Entry)** | Tài liệu Strategy và Risk Register được duyệt | Đối soát phiên bản chính thức trong thư mục `docs/` | [ĐỀ XUẤT: Đã phê duyệt v0.1] | Định hướng chuẩn mực cho việc viết kịch bản |
| **Tiêu chí ra (Exit)** | Tỷ lệ ca kiểm thử đạt trong Smoke Suite | Báo cáo Allure / TestNG của `smoke.xml` | [ĐỀ XUẤT: Đạt 100% Pass] | Đảm bảo toàn bộ luồng then chốt hoạt động hoàn hảo |
| **Tiêu chí ra (Exit)** | Tỷ lệ ca kiểm thử đạt trong Regression Suite | Báo cáo Allure / TestNG của `regression.xml` | [ĐỀ XUẤT: Đạt >= 95% Pass] | Cho phép tối đa 5% lỗi thứ yếu không nghiêm trọng |
| **Tiêu chí ra (Exit)** | Độ bao phủ các tính năng có rủi ro Critical/High | Ma trận đối chiếu ca kiểm thử với Risk Register | [ĐỀ XUẤT: Đạt 100% rủi ro cao] | Kiểm soát toàn bộ 14 rủi ro Critical & High |
| **Tiêu chí ra (Exit)** | Số lượng lỗi nghiêm trọng còn mở (Blocker/Critical) | Bảng theo dõi lỗi Defect Log | [ĐỀ XUẤT: 0 lỗi S1 và S2 còn mở] | Không bàn giao sản phẩm khi còn lỗi tê liệt hệ thống |
| **Tiêu chí ra (Exit)** | Tỷ lệ ca kiểm thử không ổn định (Flaky Tests) | Thống kê từ Allure TestListener qua 3 lần chạy | [ĐỀ XUẤT: Tỷ lệ <= 2%] | Đảm bảo độ tin cậy tuyệt đối của bộ kiểm thử (RSK-018) |
| **Tiêu chí ra (Exit)** | Hoàn thiện sản phẩm bàn giao đồ án | Kiểm tra danh mục tài liệu và artifact báo cáo | [ĐỀ XUẤT: Đủ 100% tài liệu theo DoD] | Đáp ứng trọn vẹn tiêu chuẩn nghiệm thu đồ án |

---

## 10. Sản phẩm bàn giao (Test Deliverables)

Bảng tổng hợp các sản phẩm bàn giao của chu kỳ kiểm thử theo từng mốc thời gian và định nghĩa hoàn thành (DoD):

| Sản phẩm bàn giao | Định dạng | Vị trí lưu trữ trong repository | Mốc bàn giao | Định nghĩa hoàn thành (DoD) |
|---|---|---|:---:|---|
| Kế hoạch kiểm thử chi tiết | Markdown, HTML | `docs/03-test-plan.md` & `.html` | `MS-01` | Đủ 22 mục quy chuẩn, WBS khả thi, script verify PASS 100% |
| Mã nguồn kịch bản kiểm thử tự động | Java Code | `src/test/java/projectHRM/` | `MS-05` | Tuân thủ POM, loại bỏ sleep tĩnh, 100% test độc lập dữ liệu |
| Bộ cấu hình Suite XML | TestNG XML | `src/test/resources/suites/` | `MS-06` | Phân tách rõ ràng smoke.xml, regression.xml, e2e.xml |
| Báo cáo kết quả kiểm thử tự động | HTML Report | `reports/` & `target/allure-results` | `MS-07` | Trích xuất biểu đồ xu hướng Allure và log chi tiết ExtentReports |
| Video và hình ảnh bằng chứng lỗi | Video AVI, Ảnh PNG | `reports/record/` & `reports/screenshots/` | `MS-07` | Đính kèm tự động cho 100% các ca kiểm thử thất bại |
| Sổ theo dõi và phân loại lỗi | Markdown | `docs/defect-log.md` [ĐỀ XUẤT] | `MS-07` | Ghi nhận chi tiết các bug phát hiện kèm bước tái hiện |
| Báo cáo nghiệm thu tổng kết kiểm thử | Markdown, HTML | `docs/04-test-summary-report.md` | `MS-08` | Tổng kết toàn diện độ phủ, KPI, đánh giá chất lượng đồ án |

---

## 11. Môi trường kiểm thử (Test Environment)

### 11.1 Cấu hình phần cứng và hạ tầng thực thi
- **Máy trạm kiểm thử**: Laptop cá nhân của tác giả (CPU Intel Core i7 / AMD Ryzen 7, RAM 16GB, ổ cứng SSD NVMe).
- **Hệ điều hành**: Microsoft Windows 11 64-bit.
- **Môi trường phần mềm & runtime**: Java Development Kit 25 (JDK 25), Apache Maven 3.10.0.

### 11.2 Ma trận trình duyệt và WebDriver
- **Trình duyệt mục tiêu chính**: Google Chrome phiên bản mới nhất (chế độ tiêu chuẩn và chế độ Headless trên CI).
- **Trình duyệt phụ**: Microsoft Edge Chromium (chạy kiểm tra tương thích).
- **Cơ chế quản lý Driver**: Sử dụng Selenium Manager tự động tải và đồng bộ phiên bản ChromeDriver phù hợp với trình duyệt, ngăn chặn lỗi lệch phiên bản (`RSK-024`).

### 11.3 Quản lý tài khoản kiểm thử
- **Tài khoản quản trị viên (Admin)**: Vai trò Quản trị toàn quyền hệ thống HRM, sử dụng thông tin định danh mẫu đã cấp phép, cấu hình trong file `Configs.properties`. Tuyệt đối không lưu trữ thông tin thẻ hay mật khẩu cá nhân thật trong mã nguồn.
- **Tài khoản khách hàng (Client)**: Được tạo tự động theo từng kịch bản kiểm thử với tên người dùng ngẫu nhiên dạng `client_<timestamp>`.

### 11.4 Quy trình xác minh tính sẵn sàng của môi trường
Trước mỗi đợt chạy kiểm thử chính, tester thực thi kịch bản `PreFlightCheck`:
1. Gửi yêu cầu HTTP GET đến `https://hrm.anhtester.com/` kiểm tra mã phản hồi HTTP 200 trong vòng dưới 5 giây.
2. Thực hiện đăng nhập thử tài khoản admin, kiểm tra điều hướng thành công vào màn hình Dashboard.
3. Nếu hai bước trên đạt yêu cầu, môi trường được gắn nhãn "Sẵn sàng (READY)" để kích hoạt suite tiếp theo.

---

## 12. Kế hoạch dữ liệu test (Test Data Plan)

### 12.1 Nguồn dữ liệu kiểm thử
- **Tệp bảng tính Excel tĩnh**: Sử dụng file `src/test/resources/testdata/LoginHRM.xlsx` chứa các tập dữ liệu mẫu phân chia theo sheet: `Login`, `Clients`, `Employees`, `Projects`, `Tasks`.
- **Dữ liệu động (Dynamic Data)**: Các trường thông tin định danh duy nhất (Username, Email, Tên dự án, Tên tác vụ) được sinh tự động bằng mã Java kết hợp tiền tố `AUTOTEST_` và `System.currentTimeMillis()`.

### 12.2 Chính sách dữ liệu nguyên tử độc lập (Atomic Data Policy)
- Triệt tiêu hoàn toàn sự phụ thuộc vào dòng cố định `row = 2` trong file Excel (`RSK-017`).
- Mỗi bài test tự chịu trách nhiệm khởi tạo dữ liệu phục vụ cho kịch bản của mình, không sử dụng lại bản ghi của bài test khác.
- Sau khi kiểm thử thành công, kịch bản tự động gọi phương thức xoá bản ghi để dọn dẹp sạch sẽ môi trường.

### 12.3 Xử lý các bài test có tính chất phụ thuộc (Dependent Tests)
- Với các tính năng yêu cầu dữ liệu chuỗi (ví dụ: Tạo Client -> Sửa Client -> Xoá Client), tổ chức gộp thành một kịch bản luồng khép kín (Flow Function) duy nhất để đảm bảo tính liên tục và kiểm soát toàn bộ vòng đời dữ liệu.

---

## 13. Lịch trình và mốc (Schedule and Milestones)

Lịch trình thực thi được phân bổ chi tiết trong **10 tuần (từ ngày 2026-11-02 đến ngày 2027-01-10)**. Các đầu việc trên **Đường găng (Critical Path)** mang tính quyết định tiến độ của toàn bộ dự án:

| Tuần | Khoảng ngày | Hoạt động kiểm thử chính | Sản phẩm dự kiến | Mốc bàn giao | Phụ thuộc | Công sức | Đường găng |
|:---:|---|---|---|:---:|:---:|:---:|:---:|
| **1** | 2026-11-02 → 2026-11-08 | Thiết lập môi trường, viết Healthcheck, chuẩn hóa test data Excel | Script Healthcheck, file Excel chuẩn | `MS-01` | *(Bắt đầu)* | 14.00h | **Có (CP)** |
| **2** | 2026-11-09 → 2026-11-15 | Refactor phân hệ Auth và Dashboard, loại bỏ hoàn toàn sleep tĩnh | Suite Auth & Dashboard không sleep | `MS-02` | `MS-01` | 15.00h | **Có (CP)** |
| **3** | 2026-11-16 → 2026-11-22 | Tái cấu trúc phân hệ Client sang dữ liệu nguyên tử độc lập | ManageClientsTest độc lập dữ liệu | *(none)* | `MS-02` | 16.00h | **Có (CP)** |
| **4** | 2026-11-23 → 2026-11-29 | Hoàn thiện phân hệ Employee và xây dựng suite dọn dẹp dữ liệu | EmployeesTest & Cleanup Suite | `MS-03` | Tuần 3 | 14.00h | Không |
| **5** | 2026-11-30 → 2026-12-06 | Tái cấu trúc phân hệ Project, upload file an toàn và củng cố locator | ProjectsPageTest chuẩn hóa | *(none)* | `MS-03` | 16.00h | **Có (CP)** |
| **6** | 2026-12-07 → 2026-12-13 | Tự động hóa phân hệ Task, kiểm tra vòng đời trạng thái và budget hours | TaskPageTest & Cleanup Task | `MS-04` | Tuần 5 | 15.00h | **Có (CP)** |
| **7** | 2026-12-14 → 2026-12-20 | Tối ưu kịch bản tích hợp liên thông E2E Flow và cơ chế RetryAnalyzer | FN_E2EFLowTest ổn định | `MS-05` | `MS-04` | 16.00h | **Có (CP)** |
| **8** | 2026-12-21 → 2026-12-27 | Kiểm thử tương thích Chrome/Edge, tinh chỉnh cấu hình Surefire | pom.xml chuẩn hóa, cross-browser report | `MS-06` | `MS-05` | 15.00h | Không |
| **9** | 2026-12-28 → 2027-01-03 | Chạy kiểm thử hồi quy toàn diện, phân loại lỗi và đối soát Defect | Báo cáo Allure tổng thể, Defect Log | `MS-07` | `MS-05` | 16.00h | **Có (CP)** |
| **10** | 2027-01-04 → 2027-01-10 | Kiểm thử xác nhận lần cuối, đóng gói báo cáo và nghiệm thu đồ án | Test Summary Report, hoàn tất đồ án | `MS-08` | `MS-07` | 15.00h | **Có (CP)** |

---

## 14. Ước lượng công sức (Effort Estimation & WBS)

Bảng phân rã cấu trúc công việc (WBS) chi tiết cho 20 gói đầu việc (`T01` đến `T20`) áp dụng phương pháp ước lượng ba điểm PERT:
- $O$ = Ước lượng lạc quan (Optimistic)
- $M$ = Ước lượng khả dĩ nhất (Most Likely)
- $P$ = Ước lượng bi quan (Pessimistic)
- Công thức giá trị kỳ vọng: $E = \frac{O + 4M + P}{6}$ (làm tròn 2 chữ số thập phân).

| Task ID | Task | Week | O | M | P | E | Depends on | Deliverable | Milestone | Risks |
|---|---|:---:|:---:|:---:|:---:|:---:|---|---|:---:|---|
| T01 | Thiết lập môi trường và cấu hình kịch bản Pre-flight Healthcheck | 1 | 6 | 8 | 10 | 8.00 | | Script Pre-flight Healthcheck và BaseTest hoàn thiện | MS-01 | RSK-023 |
| T02 | Kiểm tra đối soát bối cảnh và cập nhật bộ test data Excel | 1 | 4 | 6 | 8 | 6.00 | T01 | File test data LoginHRM.xlsx chuẩn hóa | | RSK-014 |
| T03 | Refactor bộ test phân hệ Auth loại bỏ sleep tĩnh và bổ sung phân quyền | 2 | 8 | 10 | 12 | 10.00 | T01, T02 | Suite Auth refactored không sleep | | RSK-001, RSK-002, RSK-018 |
| T04 | Tối ưu hóa điều hướng Dashboard và kiểm tra responsive UI | 2 | 3 | 5 | 7 | 5.00 | T03 | DashboardPage không sleep(9) | MS-02 | RSK-003, RSK-004 |
| T05 | Tái cấu trúc phân hệ Client sang cơ chế dữ liệu nguyên tử độc lập | 3 | 9 | 12 | 15 | 12.00 | T03 | ManageClientsTest độc lập dữ liệu timestamp | | RSK-005, RSK-017 |
| T06 | Kiểm thử âm tính và kiểm thử biên cho phân hệ Client | 3 | 3 | 4 | 5 | 4.00 | T05 | Bộ test case validation Client | | RSK-005, RSK-006 |
| T07 | Xây dựng kịch bản kiểm thử phân hệ Employee và kiểm tra phân công team | 4 | 7 | 10 | 13 | 10.00 | T05 | EmployeesTest hoàn chỉnh với xác thực team | MS-03 | RSK-007, RSK-008 |
| T08 | Triển khai kịch bản dọn dẹp dữ liệu rác tự động cho Client và Employee | 4 | 3 | 4 | 5 | 4.00 | T07 | Suite Cleanup Clients và Staff | | RSK-026 |
| T09 | Tái cấu trúc phân hệ Project xử lý upload tệp an toàn và logic ngày tháng | 5 | 9 | 12 | 15 | 12.00 | T07 | ProjectsPageTest chuẩn hóa không sleep cứng | | RSK-009, RSK-010, RSK-021 |
| T10 | Refactor các locator XPath mong manh trong ProjectsPage | 5 | 3 | 4 | 5 | 4.00 | T09 | Page Object Projects với semantic locator | | RSK-020 |
| T11 | Xây dựng kịch bản kiểm thử phân hệ Task và kiểm tra vòng đời trạng thái | 6 | 8 | 11 | 14 | 11.00 | T09 | TaskPageTest hoàn chỉnh với State Transition | MS-04 | RSK-011, RSK-012 |
| T12 | Hoàn thiện kịch bản dọn dẹp dữ liệu rác cho Project và Task | 6 | 3 | 4 | 5 | 4.00 | T11 | Cleanup Suite cho Project và Task | | RSK-026 |
| T13 | Tối ưu hóa và kiểm thử luồng tích hợp End-to-End xuyên suốt liên phân hệ | 7 | 10 | 13 | 16 | 13.00 | T11 | FN_E2EFLowTest chạy ổn định 100% | MS-05 | RSK-017, RSK-018 |
| T14 | Kiểm tra và cấu hình cơ chế RetryAnalyzer giới hạn tối đa 1 lần | 7 | 2 | 3 | 4 | 3.00 | T13 | Lớp TestListener tích hợp RetryAnalyzer | | RSK-025 |
| T15 | Kiểm thử tương thích đa trình duyệt Chrome và Microsoft Edge | 8 | 8 | 11 | 14 | 11.00 | T13 | Báo cáo kết quả cross-browser Chrome và Edge | | RSK-024 |
| T16 | Tinh chỉnh cấu hình Maven Surefire ngăn ngừa lỗi xanh giả và NPE | 8 | 3 | 4 | 5 | 4.00 | T15 | File pom.xml hoàn thiện với failIfNoTests | MS-06 | RSK-019, RSK-022 |
| T17 | Chạy kiểm thử hồi quy toàn diện và đo lường thời gian thực thi các suite | 9 | 9 | 12 | 15 | 12.00 | T16 | Báo cáo Allure và ExtentReports đợt hồi quy tổng thể | MS-07 | RSK-018, RSK-025 |
| T18 | Phân loại, ghi nhận và xử lý các lỗi phát sinh (Defect Triage) | 9 | 3 | 4 | 5 | 4.00 | T17 | Bảng Defect Log cập nhật trạng thái retest | | RSK-013 |
| T19 | Chạy kiểm thử xác nhận lần cuối toàn bộ Smoke, Regression và E2E | 10 | 6 | 8 | 10 | 8.00 | T17, T18 | Bộ kết quả kiểm thử chính thức đạt tiêu chí Exit | | RSK-023 |
| T20 | Đóng gói báo cáo kiểm thử, trích xuất số liệu KPI và hoàn thiện tài liệu nghiệm thu | 10 | 5 | 7 | 9 | 7.00 | T19 | Báo cáo nghiệm thu kiểm thử và tài liệu đồ án | MS-08 | RSK-030 |

### 14.1 Tổng hợp công sức và đối soát năng lực
- **Tổng công sức kỳ vọng ($E_{\text{total}}$)**: $152.00 \text{ giờ}$.
- **Hệ số dự phòng rủi ro ([ĐỀ XUẤT: 20%])**: $152.00 \times 0.20 = 30.40 \text{ giờ}$.
- **Tổng công sức yêu cầu có dự phòng**: $152.00 \times 1.20 = 182.40 \text{ giờ}$.
- **Tổng năng lực sẵn sàng của chu kỳ**: 1 người × 20 giờ/tuần × 10 tuần = **200.00 giờ**.
- **Chênh lệch quỹ thời gian an toàn**: $200.00 - 182.40 = +17.60 \text{ giờ}$ (dư 17.60 giờ làm vùng đệm bổ sung).
- **Phân bổ tải theo tuần**:
  - Tuần 1: 14.00h (≤ 20.00h)
  - Tuần 2: 15.00h (≤ 20.00h)
  - Tuần 3: 16.00h (≤ 20.00h)
  - Tuần 4: 14.00h (≤ 20.00h)
  - Tuần 5: 16.00h (≤ 20.00h)
  - Tuần 6: 15.00h (≤ 20.00h)
  - Tuần 7: 16.00h (≤ 20.00h)
  - Tuần 8: 15.00h (≤ 20.00h)
  - Tuần 9: 16.00h (≤ 20.00h)
  - Tuần 10: 15.00h (≤ 20.00h)
- **Độ dài đường găng (Critical Path)**: Tuyến các công việc phụ thuộc chính `T01 → T03 → T05 → T09 → T11 → T13 → T15 → T16 → T17 → T19 → T20` có tổng thời gian xấp xỉ **106.00 giờ**.

### 14.2 Kết luận tính khả thi và phương án cắt giảm dự phòng
- **Kết luận**: Kế hoạch kiểm thử hoàn toàn **KHẢ THI (FEASIBLE)** về mặt công sức và thời gian. Quỹ năng lực 200 giờ đủ đáp ứng toàn bộ 20 đầu việc với mức dự phòng 20% và vẫn còn dư 17.60 giờ dự phòng khẩn cấp.
- **Phương án cắt giảm khi xảy ra sự cố chậm tiến độ**:
  - Nếu thời gian bị thâm hụt quá 15 giờ: Tạm thời hoãn công việc kiểm thử tương thích đa trình duyệt Edge (`T15`, 11.00h), tập trung 100% tài nguyên cho trình duyệt chuẩn Google Chrome.
  - Nếu tiếp tục chậm tiến độ: Chuyển kịch bản kiểm thử âm tính mở rộng (`T06`, 4.00h) sang kiểm tra thăm dò thủ công nhanh.

---

## 15. Nguồn lực và vai trò (Resources and Roles)

### 15.1 Phân bổ nhân sự thực hiện
Do tính chất đồ án cá nhân, một nhân sự đảm nhận nhiều vai trò chuyên môn theo chuẩn công nghiệp:
- **Họ và tên**: Võ Quỳnh Nhi
- **Vai trò chuyên trách**:
  - *Test Manager*: Lập kế hoạch, theo dõi tiến độ WBS, quản lý rủi ro và điều phối mốc bàn giao.
  - *Automation Engineer*: Lập trình mã kiểm thử Java/Selenium, tối ưu Page Object, cấu hình TestNG XML.
  - *Defect Analyst*: Thực thi kiểm thử, phân tích lỗi, đối soát nguyên nhân gốc và kiểm tra lại bản vá.
- **Thời lượng làm việc cam kết**: 20 giờ/tuần (phân bổ linh hoạt các buổi tối và ngày cuối tuần).

### 15.2 Vai trò định hướng và phê duyệt
- **Người hướng dẫn / Mentor**: [CẦN XÁC NHẬN: Giảng viên hướng dẫn đồ án]
- **Trách nhiệm**: Đánh giá tính đúng đắn của phương pháp kiểm thử, định hướng xử lý rủi ro và phê duyệt các mốc nghiệm thu.

### 15.3 Nhu cầu đào tạo và nâng cao kỹ năng
- Nâng cao kỹ năng phân tích luồng bất đồng bộ của ứng dụng web phục vụ việc viết điều kiện chờ `WebDriverWait`.
- Nghiên cứu cơ chế cấu hình Maven Surefire trên JDK 25 để tối ưu hóa việc xuất báo cáo.

---

## 16. Quản lý lỗi trong chu kỳ (Defect Management)

### 16.1 Quy trình xử lý lỗi
Áp dụng vòng đời quản lý lỗi chuẩn đã quy định trong Test Strategy: `New → Open → In Progress → Resolved → Retest → Closed`.

### 16.2 Nhịp đối soát lỗi (Triage Rhythm)
- Định kỳ đối soát lỗi vào cuối ngày thứ Tư và thứ Bảy hàng tuần sau khi thực thi Regression Suite.
- Các lỗi mức độ Blocker (S1) hoặc Critical (S2) phải được ghi nhận và phân loại ngay trong vòng 4 giờ kể từ khi phát hiện.

### 16.3 Thỏa thuận mức dịch vụ xử lý lỗi (SLA) [ĐỀ XUẤT]
- **Mức Blocker (S1)**: Phân tích nguyên nhân và đề xuất giải pháp xử lý trong vòng [ĐỀ XUẤT: 8 giờ làm việc].
- **Mức Critical (S2)**: Xử lý và kiểm tra lại trong vòng [ĐỀ XUẤT: 24 giờ làm việc].
- **Mức Major (S3)**: Khắc phục trong vòng [ĐỀ XUẤT: 48 giờ làm việc].
- **Mức Minor (S4)**: Tích lũy xử lý trong đợt làm sạch giao diện cuối chu kỳ.

---

## 17. Báo cáo và giao tiếp (Reporting and Communication)

Bảng phân định cơ chế báo cáo và thông tin liên lạc trong suốt 10 tuần thực hiện:

| Loại báo cáo | Nội dung báo cáo | Nơi lưu trữ / Kênh gửi | Tần suất | Người nhận |
|---|---|---|---|---|
| **Báo cáo thực thi nhanh (Execution Log)** | Số lượng test pass/fail, lỗi phát sinh qua log | Terminal Maven, file `logs/app.log` | Sau mỗi lần chạy suite | Automation Tester |
| **Báo cáo tiến độ tuần (Weekly Status)** | Tiến độ WBS, mốc bàn giao đạt được, rủi ro mới | `docs/progress/` [ĐỀ XUẤT] | Hàng tuần (Chủ nhật) | Mentor / Giảng viên |
| **Báo cáo trực quan tương tác (Allure / Extent)** | Biểu đồ xu hướng, thời gian chạy từng test, screenshot | `reports/` & `target/allure-results` | Sau mỗi đợt chạy hồi quy | Tester, Ban đánh giá |
| **Báo cáo nghiệm thu tổng kết (Final Report)** | Đánh giá độ bao phủ, số liệu KPI, kết luận đồ án | `docs/04-test-summary-report.md` | Kết thúc chu kỳ (Tuần 10) | Hội đồng chấm đồ án |

---

## 18. Rủi ro của kế hoạch và dự phòng (Plan Risks and Contingencies)

Bảng nhận diện các rủi ro phát sinh trong quá trình thực thi kế hoạch kiểm thử (`TP-R01` đến `TP-R05`), gắn liền với Top 10 rủi ro trong Sổ đăng ký rủi ro:

| Mã rủi ro | Mô tả rủi ro kế hoạch | L | I | Score | Biện pháp giảm thiểu | Kế hoạch dự phòng (Contingency) | Dấu hiệu kích hoạt sớm (Trigger) | Rủi ro Top 10 liên quan |
|:---:|---|:---:|:---:|:---:|---|---|---|:---:|
| **TP-R01** | Trang web demo bị đổi mật khẩu hoặc sập dịch vụ làm gián đoạn kế hoạch | 3 | 5 | **15** | Chạy Pre-flight Healthcheck đầu mỗi ngày; duy trì dữ liệu mock offline | Chuyển sang tài khoản demo phụ hoặc liên hệ quản trị viên reset mật khẩu | Healthcheck báo lỗi 401 Unauthorized hoặc HTTP 502/503 | RSK-023 |
| **TP-R02** | Xung đột dữ liệu trên môi trường demo làm gãy các kịch bản phụ thuộc | 4 | 4 | **16** | Chuyển đổi toàn diện sang dữ liệu nguyên tử độc lập kèm timestamp | Chạy lại kịch bản riêng lẻ với bộ dữ liệu mới hoặc chạy kịch bản dọn dẹp | Kịch bản thêm khách hàng thành công nhưng bước tìm kiếm trả về sai dữ liệu | RSK-005, RSK-017 |
| **TP-R03** | Mạng internet chập chờn làm thời gian chạy regression vượt quá 15 phút | 3 | 3 | **9** | Cấu hình RetryAnalyzer tối đa 1 lần; thay toàn bộ wait động | Chuyển khung giờ thực thi suite lớn sang buổi tối muộn mạng ổn định | Xuất hiện liên tiếp các lỗi TimeoutException khi tải trang | RSK-018, RSK-025 |
| **TP-R04** | Lỗi crash Surefire NPE trên Java 25 cản trở việc chạy song song đa luồng | 3 | 4 | **12** | Duy trì cấu hình single-thread an toàn trên Maven Surefire | Chỉ chạy song song ở mức TestNG XML Suite đối với các lớp test độc lập | Maven ném ngoại lệ java.lang.NullPointerException tại GraphOrchestrator | RSK-019 |
| **TP-R05** | Sự cố cá nhân đột xuất làm giảm quỹ thời gian cam kết 20 giờ/tuần | 2 | 4 | **8** | Tận dụng vùng đệm an toàn 17.60 giờ; bám sát đường găng công việc | Kích hoạt phương án cắt giảm hoãn kiểm thử trình duyệt Edge (T15) | Tiến độ công việc trong tuần bị trễ quá 5 giờ so với kế hoạch WBS | RSK-013 |

---

## 19. Giả định, ràng buộc và phụ thuộc (Assumptions, Constraints and Dependencies)

### 19.1 Giả định của kế hoạch
- [GIẢ ĐỊNH: Môi trường web https://hrm.anhtester.com/ hoạt động liên tục và không có đợt nâng cấp cấu trúc DOM lớn trong 10 tuần].
- [GIẢ ĐỊNH: Quỹ thời gian cá nhân 20 giờ/tuần được duy trì đều đặn không bị gián đoạn kéo dài quá 1 tuần].
- [GIẢ ĐỊNH: Kết quả kiểm thử trên trình duyệt Google Chrome được hội đồng chấm điểm công nhận là môi trường chuẩn].

### 19.2 Ràng buộc của kế hoạch
- Toàn bộ chi phí thực hiện đồ án phải nằm trong nguồn lực cá nhân sẵn có; không sử dụng các dịch vụ Cloud Testing trả phí.
- Tuyệt đối không can thiệp sâu vào cơ sở dữ liệu hệ thống demo bằng các công cụ tấn công hoặc chèn mã độc.
- Thời hạn kết thúc chu kỳ vào ngày 2027-01-10 là mốc cố định không thể lùi lịch nộp đồ án.

### 19.3 Mối quan hệ phụ thuộc
- Phụ thuộc vào chất lượng mã nguồn framework và các lớp Page Object đã xây dựng trong repository.
- Phụ thuộc vào kết quả phân tích rủi ro trong Sổ đăng ký rủi ro (`docs/01-risk-register.md`) và các quyết định kỹ thuật trong Test Strategy (`docs/02-test-strategy.md`).

---

## 20. Đề xuất điều chỉnh Strategy (Proposed Strategy Adjustments)

Trong quá trình lập kế hoạch chi tiết cho chu kỳ 10 tuần, ghi nhận các đề xuất tối ưu hóa bổ sung cho Chiến lược kiểm thử:
- **Đề xuất 1**: Tạm thời chưa kích hoạt kiểm thử dịch vụ backend với RestAssured trong chu kỳ này để tập trung tối đa thời lượng hoàn thiện bộ kịch bản UI và E2E Flow.
- **Đề xuất 2**: Bổ sung bộ kịch bản dọn dẹp dữ liệu chuyên biệt (`T08`, `T12`) vào bộ suite định kỳ nhằm chủ động kiểm soát rủi ro dữ liệu rác tích tụ trên môi trường demo (`RSK-026`).

---

## 21. Phê duyệt (Sign-off and Approval)

Bảng ghi nhận chữ ký và sự đồng thuận của các bên liên quan về Kế hoạch kiểm thử:

| Vai trò dự án | Họ và tên | Ngày phê duyệt | Chữ ký xác nhận |
|---|---|:---:|:---:|
| **Tác giả / Test Manager** | Võ Quỳnh Nhi | 2026-11-02 | [CẦN XÁC NHẬN: Chữ ký tác giả] |
| **Giảng viên hướng dẫn / Mentor** | [CẦN XÁC NHẬN: Tên giảng viên] | [CẦN XÁC NHẬN: Ngày duyệt] | [CẦN XÁC NHẬN: Chữ ký giảng viên] |
| **Đại diện phản biện đồ án** | [CẦN XÁC NHẬN: Tên phản biện] | [CẦN XÁC NHẬN: Ngày duyệt] | [CẦN XÁC NHẬN: Chữ ký phản biện] |

---

## 22. Phụ lục (Appendix)

### 22.1 Ma trận Tính năng × Loại kiểm thử × Nhãn mức chạy
Bảng tổng hợp đối soát chéo phục vụ việc cấu hình các suite XML:

| Phân hệ chức năng | Kiểm thử chức năng | Kiểm thử khói | Kiểm thử hồi quy | Kiểm thử luồng E2E | Kiểm thử bảo mật/biên | Nhãn áp dụng |
|---|:---:|:---:|:---:|:---:|:---:|:---:|
| `auth` | Có | Có | Có | Có | Có | `smoke`, `regression`, `auth` |
| `dashboard` | Có | Không | Có | Có | Không | `regression`, `dashboard` |
| `client` | Có | Có | Có | Có | Có | `smoke`, `regression`, `client` |
| `employee` | Có | Không | Có | Có | Có | `regression`, `employee` |
| `project` | Có | Có | Có | Có | Có | `smoke`, `regression`, `project` |
| `task` | Có | Không | Có | Có | Có | `regression`, `task` |
| Luồng E2E Flow | Có | Không | Không | Có | Không | `e2e` |

### 22.2 Danh sách câu hỏi mở cần giải quyết
1. [CẦN ĐO: Thời gian chạy thực tế của toàn bộ Regression Suite sau khi đã loại bỏ hoàn toàn các lệnh sleep tĩnh].
2. [CẦN ĐO: Tỷ lệ flaky test thực tế ghi nhận được sau 10 lần thực thi liên tiếp trên môi trường demo].
3. [CẦN XÁC NHẬN: Quy định cụ thể của hội đồng về việc nộp video quay màn hình cho tất cả các ca kiểm thử hay chỉ các ca kiểm thử thất bại].

### 22.3 Danh mục tự kiểm tra chất lượng (Quality Gate Checklist)
- [x] Đầy đủ 22/22 mục bắt buộc theo đúng thứ tự quy chuẩn từ Mục 1 đến Mục 22.
- [x] Bảng WBS tại Mục 14 tuân thủ đầy đủ cấu trúc cột, ước lượng ba điểm O/M/P tính toán chính xác $E = (O + 4M + P) / 6$.
- [x] Tổng công sức 152.00 giờ cộng 20% dự phòng thành 182.40 giờ, nằm hoàn toàn trong giới hạn năng lực 200.00 giờ (1 người × 20h/tuần × 10 tuần).
- [x] Tải công việc từng tuần dao động từ 14.00h đến 16.00h, không có tuần nào bị quá tải vượt mức 20.00h.
- [x] Toàn bộ các mốc bàn giao (`MS-01` đến `MS-08`) đều có gói đầu việc tương ứng kèm sản phẩm cụ thể và định nghĩa hoàn thành (DoD).
- [x] Đồ thị phụ thuộc WBS hoàn toàn không có chu trình (acyclic), không có đầu việc nào xếp ở tuần sớm hơn đầu việc tiền đề.
- [x] Toàn bộ ngày tháng nằm chính xác trong khoảng chu kỳ 2026-11-02 đến 2027-01-10.
- [x] Tiêu chí vào/ra tại Mục 9 kiểm tra được, có số lượng định lượng rõ ràng và ngưỡng gắn tag `[ĐỀ XUẤT]`.
- [x] Tham chiếu đầy đủ các mã rủi ro `RSK-xxx`, mã quyết định `STR-Dxx` và mã rủi ro kế hoạch `TP-Rxx`.

---
