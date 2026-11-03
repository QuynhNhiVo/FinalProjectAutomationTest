# Test Strategy – Chiến lược kiểm thử tự động

## 1. Document Control

### 1.1 Thông tin tài liệu
- **Tên dự án**: Final Project Automation Test – Anh Tester HRM
- **Hệ thống được kiểm thử (AUT)**: Anh Tester HRM System (https://hrm.anhtester.com/)
- **Mã tài liệu**: STR-HRM-02
- **Phiên bản**: v0.1
- **Trạng thái**: Draft
- **Tác giả**: Võ Quỳnh Nhi
- **Thời gian khởi tạo**: Tháng 11 Năm 2026
- **Thời gian cập nhật**: Tháng 11 Năm 2026
- **Thời gian rà soát**: [CẦN XÁC NHẬN: Ngày rà soát chính thức]

### 1.2 Lịch sử thay đổi
| Phiên bản | Thời gian | Tác giả | Tóm tắt thay đổi |
|---|---|---|---|
| v0.1 | Tháng 11 Năm 2026 | Võ Quỳnh Nhi | Khởi tạo Chiến lược kiểm thử tự động (Test Strategy) dựa trên Sổ đăng ký rủi ro kiểm thử |

### 1.3 Tài liệu tham chiếu
- `docs/project-context.md`: Bối cảnh dự án kiểm thử tự động HRM
- `docs/01-risk-register.md`: Sổ đăng ký rủi ro kiểm thử (Risk Register)
- Chuẩn kiểm thử phần mềm quốc tế ISTQB CTFL & Advanced Level Test Manager
- Chuẩn kiến trúc tự động hóa ISTQB Test Automation Engineer
- Tiêu chuẩn ISO/IEC/IEEE 29119-3 (Test Documentation) & ISO/IEC 25010 (Product Quality Model)

---

## 2. Giới thiệu (Introduction)

### 2.1 Mục đích tài liệu
Tài liệu Chiến lược kiểm thử (Test Strategy) này định nghĩa phương pháp tiếp cận toàn diện, dài hạn và ổn định cho dự án kiểm thử tự động hệ thống Anh Tester HRM. Chiến lược trả lời câu hỏi cốt lõi: *"Kiểm thử theo cách nào, ở cấp độ nào, sử dụng kiến trúc gì và vì sao lại lựa chọn phương án đó"*. Tài liệu đóng vai trò kim chỉ nam cho việc lập Kế hoạch kiểm thử (Test Plan) và xây dựng Ma trận truy vết yêu cầu (RTM) ở các bước tiếp theo.

### 2.2 Đối tượng độc giả
- **QA Lead / Automation Tester**: Nắm vững phương pháp thiết kế, kiến trúc framework, chính sách dữ liệu và quy chuẩn mã kiểm thử.
- **Giảng viên hướng dẫn / Mentor**: Thẩm định tính đúng đắn về mặt học thuật, tính thực tiễn của giải pháp và mức độ bao phủ rủi ro.
- **Hội đồng đánh giá đồ án**: Đối soát cơ sở kỹ thuật của các quyết định kiến trúc và tiêu chí đánh giá chất lượng sản phẩm.

### 2.3 Tổng quan sản phẩm được kiểm thử
Hệ thống Anh Tester HRM (`https://hrm.anhtester.com/`) là ứng dụng web quản trị doanh nghiệp (ERP/HRM) đa người dùng, tích hợp các nghiệp vụ quản lý tài nguyên nhân lực, thông tin khách hàng, phân bổ dự án và giao việc tác vụ. Ứng dụng vận hành trên môi trường demo công khai với dữ liệu chia sẻ cộng đồng.

### 2.4 Phạm vi kiểm thử (Scope)

#### 2.4.1 Trong phạm vi (IN-SCOPE)
- Kiểm thử chức năng (Functional Testing) trên 6 phân hệ cốt lõi:
  - `auth`: Xác thực đăng nhập Admin, đăng nhập Client bằng tài khoản tạo mới, kiểm tra validation khi sai thông tin và đăng xuất an toàn.
  - `dashboard`: Kiểm tra điều hướng và hiển thị các widget thống kê tổng quan sau khi xác thực.
  - `client`: Luồng quản lý khách hàng (thêm mới, cập nhật thông tin, tìm kiếm, kiểm tra dữ liệu chi tiết và xoá khách hàng).
  - `employee`: Luồng quản lý nhân sự (thêm mới nhân viên, tìm kiếm và phân công nhân sự vào dự án).
  - `project`: Quản lý dự án (thêm mới dự án, đính kèm tệp tin tài liệu, cập nhật trạng thái tiến độ và xoá dự án).
  - `task`: Quản lý công việc (thêm công việc trực thuộc dự án, chuyển trạng thái và xoá công việc).
- Kiểm thử tích hợp luồng dài khép kín (End-to-End Integration Flow): Luồng nghiệp vụ xuyên suốt từ tạo Client -> gán Employee -> tạo Project -> tạo Task -> đính kèm tài liệu -> cập nhật Status -> dọn dẹp dữ liệu.
- Kiểm thử an toàn thông tin mức giao diện: Kiểm tra khả năng xử lý chuỗi ký tự đặc biệt, ngăn ngừa injection cơ bản trên form nhập liệu và kiểm tra phân quyền URL quản trị.
- Kiểm thử độ tương thích giao diện trên trình duyệt chuẩn (Google Chrome) và hỗ trợ đa trình duyệt (Edge, Firefox).

#### 2.4.2 Ngoài phạm vi (OUT-OF-SCOPE kèm lý do cụ thể)
- **Phân hệ Tiền lương (Payroll) & Điểm danh (Attendance)**: Chưa đưa vào tự động hóa ở giai đoạn này nhằm tập trung nguồn lực chuyên sâu vào chuỗi nghiệp vụ cốt lõi Client - Project - Task của đồ án.
- **Kiểm thử tải nặng và hiệu năng chịu tải (Load / Stress Testing)**: Nghiêm cấm thực hiện trên hệ thống demo công khai `hrm.anhtester.com` để bảo vệ tài nguyên máy chủ đào tạo dùng chung và tránh nguy cơ sập dịch vụ.
- **Kiểm thử bảo mật thâm nhập sâu (Penetration Testing / Exploitation)**: Chỉ dừng lại ở mức validation dữ liệu đầu vào và phân quyền cơ bản; không quét lỗ hổng hạ tầng hay tấn công dịch vụ từ chối (DoS).
- **Kiểm thử trên thiết bị di động thật (Real Mobile Device Testing)**: Tập trung tối ưu hóa cho môi trường web máy tính để bàn (Desktop Web) theo yêu cầu chuẩn của đồ án.

### 2.5 Mục tiêu kiểm thử dạng SMART gắn với rủi ro
- **Specific (Cụ thể)**: Bao phủ 100% Top 10 rủi ro nghiêm trọng nhất trong Sổ đăng ký rủi ro (đặc biệt là các rủi ro RSK-001, RSK-005, RSK-010, RSK-017, RSK-018, RSK-023).
- **Measurable (Đo lường được)**: Đạt tỷ lệ tự động hóa kịch bản hồi quy $\ge 85\%$, tỷ lệ chạy thành công (Pass Rate) $\ge 95\%$, tỷ lệ bài test không ổn định (Flaky Rate) $\le 2\%$.
- **Achievable (Khả thi)**: Xây dựng trên nền tảng công nghệ sẵn có của repository gồm Java 25, Selenium WebDriver 4.31, TestNG 7.12 và mô hình Page Object Model.
- **Relevant (Thực tế & Phù hợp)**: Triệt tiêu các vấn đề cố hữu đã được nhận diện như phụ thuộc dữ liệu cứng trong Excel (RSK-017), lạm dụng sleep tĩnh (RSK-018) và bẫy xanh giả trong build (RSK-022).
- **Time-bound (Theo giai đoạn)**: Hoàn thành thiết lập khung chiến lược và kịch bản cốt lõi trong Giai đoạn Hiện tại, mở rộng kịch bản nâng cao trong Giai đoạn Kế tiếp và hoàn thiện tài liệu nghiệm thu trong Giai đoạn Sau này.

---

## 3. Cách tiếp cận tổng thể (Overall Approach)

### 3.1 Kiểm thử dựa trên rủi ro (Risk-Based Testing)
Chiến lược kiểm thử được dẫn dắt trực tiếp từ kết quả phân tích tại Sổ đăng ký rủi ro (`docs/01-risk-register.md`):
- **Nhóm rủi ro Critical (Điểm 20–25)**: Tập trung vào rủi ro môi trường demo chia sẻ bị đổi mật khẩu hoặc sập dịch vụ (RSK-023). Biện pháp chiến lược là thiết lập kịch bản kiểm tra sức khỏe đầu chu kỳ (Pre-flight Healthcheck) thực thi trước mọi suite test.
- **Nhóm rủi ro High (Điểm 10–16)**: Bao gồm rủi ro phân quyền chéo (RSK-001), trùng lặp định danh khách hàng (RSK-005), upload file mã độc (RSK-010), dữ liệu phụ thuộc gãy dây chuyền (RSK-017), sleep tĩnh gây chậm (RSK-018) và sự cố Java 25 Surefire NPE (RSK-019). Toàn bộ nhóm này được phân bổ độ sâu kiểm thử Chuyên sâu (Extensive) hoặc Cân bằng (Balanced), bắt buộc tự động hóa và chạy trong suite smoke hoặc regression hàng ngày.
- **Nhóm rủi ro Medium (Điểm 5–9)**: Phân bổ độ sâu Cân bằng (Balanced) với các kỹ thuật phân tích biên và bảng quyết định trong bộ kiểm thử hồi quy.
- **Nhóm rủi ro Low (Điểm 1–4)**: Phân bổ độ sâu Cơ hội (Opportunity-based), kiểm tra thăm dò khi có tài nguyên dư.

### 3.2 Nguyên tắc chỉ đạo (Guiding Principles)
1. **Chuyển dịch về phía trước (Shift-Left Focus)**: Bắt lỗi validation dữ liệu và điều hướng ngay tại các thao tác giao diện đầu vào trước khi lưu xuống cơ sở dữ liệu.
2. **Tính độc lập nguyên tử của kịch bản (Test Atomicity & Independence)**: Tuyệt đối không để kịch bản này dựa vào kết quả thực thi hay trạng thái dữ liệu tạo ra từ kịch bản trước. Mỗi bài test phải tự quản lý vòng đời dữ liệu của mình.
3. **Minh bạch và không che giấu lỗi**: Mọi thất bại kiểm thử phải được hiển thị chính xác. Tuyệt đối không sử dụng các cờ cấu hình bỏ qua lỗi để ép build thành công.
4. **Độ ổn định cao hơn số lượng**: Ưu tiên 20 bài test chạy ổn định, tin cậy tuyệt đối thay vì 100 bài test chập chờn (flaky) gây nhiễu loạn kết quả báo cáo.

### 3.3 Cách tiếp cận khi hệ thống thiếu tài liệu đặc tả yêu cầu chính thức
Do hệ thống AUT là trang web đào tạo demo không cung cấp tài liệu SRS/BRD chi tiết (RSK-014), chiến lược xử lý được quy chuẩn như sau:
- **Kiểm thử thăm dò thiết lập đường chuẩn (Exploratory Baseline Testing)**: Tester thực hiện thao tác thủ công, ghi hình và chụp ảnh các luồng nghiệp vụ chuẩn để xác lập hành vi mặc định (Expected Behavior Baseline).
- **Kỹ thuật Black-box suy luận nghiệp vụ**: Áp dụng chuẩn nghiệp vụ quản trị nhân sự ERP thông thường (ví dụ: ngày kết thúc không được nhỏ hơn ngày bắt đầu, email phải có định dạng hợp lệ, xóa cha phải xử lý ràng buộc khóa ngoại con).
- **Quy trình quản lý thay đổi hành vi**: Khi hệ thống có sự thay đổi giao diện hoặc luồng xử lý, tester ghi nhận vào danh sách `[CẦN XÁC NHẬN]`, lập biên bản đối soát với giảng viên hướng dẫn trước khi cập nhật mã kiểm thử.

---

## 4. Cấp độ và loại kiểm thử (Test Levels and Test Types)

Bảng ma trận dưới đây phân định rõ các loại kiểm thử, cấp độ áp dụng, mức độ ưu tiên, công cụ thực thi và lộ trình triển khai qua các giai đoạn:

| Loại kiểm thử | Cấp độ | Trong/Ngoài phạm vi | Ưu tiên | Phương pháp tiếp cận | Công cụ thực thi | Nhãn mức chạy | Giai đoạn triển khai |
|---|---|:---:|:---:|---|---|:---:|:---:|
| Kiểm thử chức năng | System | Trong phạm vi | High | Tự động hóa kiểm thử hộp đen dựa trên giao diện người dùng | Selenium Java, TestNG, WebUI | regression | Giai đoạn Hiện tại |
| Kiểm thử khói | System | Trong phạm vi | Critical | Tự động hóa các đường dẫn chính then chốt, chạy cực nhanh | Selenium Java, TestNG | smoke | Giai đoạn Hiện tại |
| Kiểm thử hồi quy | System | Trong phạm vi | High | Tự động hóa bao phủ toàn bộ chức năng và trường hợp biên | Selenium Java, TestNG, Apache POI | regression | Giai đoạn Hiện tại |
| Kiểm thử luồng tích hợp | System Integration | Trong phạm vi | High | Tự động hóa kịch bản nghiệp vụ liên thông xuyên phân hệ | Selenium Java, TestNG, Page Objects | e2e | Giai đoạn Hiện tại |
| Kiểm thử phân quyền & an toàn | System | Trong phạm vi | High | Kiểm thử bảo mật hộp đen, kiểm tra truy cập URL trái phép | Selenium Java, TestNG | regression | Giai đoạn Hiện tại |
| Kiểm thử tương thích trình duyệt | System | Trong phạm vi | Medium | Chạy kịch bản hồi quy trên nhiều trình duyệt khác nhau | WebDriver, BaseTest (Chrome, Edge, Firefox) | regression | Giai đoạn Kế tiếp |
| Kiểm thử giao diện & độ tương thích | System | Ngoài phạm vi | Low | Kiểm tra thăm dò bố cục hiển thị trên các độ phân giải | Manual Exploratory | N/A | Giai đoạn Kế tiếp |
| Kiểm thử hiệu năng & tải | System | Ngoài phạm vi | Low | Đo thời gian tải trang cơ bản, không tạo tải nặng | Selenium Page Load Profiling | N/A | Giai đoạn Sau này |
| Kiểm thử khả năng tiếp cận | System | Ngoài phạm vi | Low | Đánh giá độ tương phản màu sắc và cấu trúc nhãn nhập liệu | Manual / Browser DevTools | N/A | Giai đoạn Sau này |
| Kiểm thử dịch vụ giao diện lập trình | Integration | Ngoài phạm vi | Medium | Đề xuất kiểm thử trực tiếp tầng API backend của hệ thống | RestAssured (đề xuất bổ sung) | N/A | Giai đoạn Kế tiếp |

---

## 5. Kỹ thuật thiết kế test (Test Design Techniques)

Chiến lược áp dụng các kỹ thuật thiết kế ca kiểm thử hộp đen chuẩn ISTQB, ánh xạ trực tiếp vào từng phân hệ với các trường hợp cụ thể:

| Kỹ thuật thiết kế | Phân hệ áp dụng | Ví dụ áp dụng thực tế trên hệ thống Anh Tester HRM | Căn cứ nghiệp vụ / Rủi ro giải quyết |
|---|---|---|---|
| Phân vùng tương đương | `auth` | Chia tập Username thành: hợp lệ (tồn tại), không tồn tại, để trống; Password thành: đúng, sai, để trống | Xác thực người dùng và bắt lỗi đăng nhập kịp thời (RSK-002) |
| Phân tích giá trị biên | `project` | Kiểm tra khoảng ngày: Start Date bằng End Date, Start Date nhỏ hơn End Date 1 ngày, Start Date lớn hơn End Date 1 ngày | Tránh lỗi logic thời gian thực hiện dự án (RSK-009) |
| Phân vùng tương đương | `client` | Định dạng Email: email chuẩn (`user@domain.com`), email thiếu `@`, email thiếu tên miền, email chứa ký tự khoảng trắng | Đảm bảo tính hợp lệ của dữ liệu liên lạc khách hàng (RSK-005) |
| Phân tích giá trị biên | `task` | Trường số giờ ngân sách (budget hours): giá trị 0, giá trị 1, giá trị cực đại 9999, giá trị âm (-1) | Ngăn ngừa lỗi tính tổng ngân sách dự án bị âm (RSK-012) |
| Bảng quyết định | `employee` | Điều kiện phân công nhân sự vào dự án: Nhân viên Active + Đúng phòng ban -> Hiển thị; Nhân viên Inactive -> Chặn hiển thị | Tránh gán nhầm nhân sự đã nghỉ việc vào dự án (RSK-008) |
| Bảng chuyển trạng thái | `task` | Vòng đời trạng thái công việc: Not Started -> In Progress -> Completed; kiểm tra trạng thái Closed không cho phép sửa giờ | Bảo đảm tính toàn vẹn dữ liệu nghiệm thu công việc (RSK-011) |
| Phân loại lỗi | `project` | Tải tệp đính kèm: file rỗng 0 byte, file có tên cực dài, file có đuôi mở rộng bị cấm (.exe, .bat, script độc hại) | Phòng ngừa tấn công tải tệp và sập server (RSK-010) |
| Kiểm thử thăm dò | `dashboard` | Điều chỉnh thu phóng cửa sổ trình duyệt, kiểm tra hiển thị thanh menu và widget khi dữ liệu dự án rỗng | Đảm bảo khả năng phản hồi giao diện ổn định (RSK-004) |

---

## 6. Chiến lược tự động hoá (Automation Strategy)

### 6.1 Tiêu chí chọn và không chọn tự động hóa
- **Tiêu chí ưu tiên tự động hóa**:
  - Các kịch bản chạy lặp lại thường xuyên trong mọi đợt kiểm thử (Smoke Suite, Regression Suite).
  - Các luồng nghiệp vụ quan trọng có tác động tài chính hoặc vận hành cao (Xác thực, Tạo khách hàng, Quản lý dự án).
  - Các ca kiểm thử yêu cầu lặp lại trên nhiều bộ dữ liệu đầu vào (Data-Driven Testing qua file Excel).
  - Các kịch bản kiểm thử phân quyền và xác thực biên.
- **Tiêu chí không tự động hóa**:
  - Các kiểm tra mang tính cảm tính thẩm mỹ giao diện (màu sắc, độ mềm font chữ).
  - Các chức năng đang trong quá trình thay đổi giao diện hàng ngày chưa ổn định DOM.
  - Các thao tác một lần có chi phí tự động hóa cao hơn nhiều so với thao tác thủ công.

### 6.2 Kiến trúc framework theo tầng và quy tắc thiết kế
Framework được xây dựng theo kiến trúc phân tầng chuyên biệt nhằm đảm bảo tính module hóa và dễ bảo trì:
1. **Tầng khởi tạo và quản lý Driver (Driver Management Layer)**: Lớp `DriverManager` quản lý đối tượng WebDriver thông qua `ThreadLocal<WebDriver>`, đảm bảo mỗi luồng thực thi sở hữu một phiên trình duyệt độc lập. Lớp `BaseTest` điều khiển vòng đời khởi tạo `@BeforeMethod` và giải phóng `@AfterMethod`.
2. **Tầng từ khóa tiện ích (Utility & Keyword Layer)**: Lớp `WebUI` đóng gói toàn bộ thao tác Selenium thành các từ khóa mức cao, tích hợp ghi log Log4j và chụp ảnh màn hình tự động. Các lớp `ExcelHelpers`, `PropertiesHelpers`, `CaptureHelpers`, `RecordHelpers` hỗ trợ đọc cấu hình, xuất báo cáo và ghi video.
3. **Tầng mô hình trang (Page Object Layer)**: Các lớp trang kế thừa `CommonPage` (như `LoginPage`, `ManageClientsPage`, `ProjectsPage`...). Toàn bộ locator By được khai báo private; các phương thức trang trả về chính đối tượng trang (Fluent Design Pattern) để tạo chuỗi hành động liền mạch.
4. **Tầng kịch bản kiểm thử (Test Case Layer)**: Các lớp kiểm thử kế thừa `BaseTest` (như `LoginTest`, `ManageClientsTest`...), chỉ chứa logic nghiệp vụ và lời gọi phương thức từ Page Object, tuyệt đối không chứa mã Selenium thô.

**Quy tắc thiết kế bắt buộc**:
- **Quy tắc Locator bền vững**: Cấm sử dụng XPath tuyệt đối dạng chỉ mục tầng (`/html/body/div[1]/...`) hoặc nối chuỗi cha con mong manh. Bắt buộc dùng `id`, `name`, semantic CSS selector hoặc XPath tương đối kết hợp thuộc tính ngữ nghĩa ổn định.
- **Quy tắc chờ đợi linh hoạt (Explicit Wait)**: Loại bỏ hoàn toàn các lệnh dừng tĩnh `sleep()` cứng trong toàn bộ mã nguồn. 100% các điểm đồng bộ hóa phải sử dụng `WebDriverWait` với các điều kiện chuẩn: `visibilityOfElementLocated`, `elementToBeClickable`, `presenceOfElementLocated`.
- **Quy tắc đặt Assertion**: Mọi điểm kiểm tra kết quả (Assert) được tổ chức rõ ràng trong phương thức xác minh của Page Object hoặc trực tiếp trong Test Case; không rải assert tùy tiện trong các hàm tiện ích.

### 6.3 Chính sách dữ liệu kiểm thử (Test Data Policy)
- **Tính độc lập nguyên tử (Atomic Data Policy)**: Giải quyết dứt điểm rủi ro RSK-017. Kịch bản kiểm thử không được dựa vào các hàng dữ liệu cố định trong Excel. Mỗi kịch bản khi thực thi phải tự động sinh chuỗi định danh duy nhất (sử dụng tiền tố `AUTOTEST_` kết hợp thời gian hệ thống mili-giây).
- **Quy chuẩn dọn dẹp dữ liệu (Self-cleaning Teardown)**: Mọi kịch bản tạo dữ liệu thành công phải có bước dọn dẹp (xoá bản ghi vừa tạo) ở cuối luồng để tránh làm rác môi trường demo dùng chung.
- **Xử lý kịch bản phụ thuộc**: Với các thao tác bắt buộc cần dữ liệu tiền đề (như Sửa, Xoá), ưu tiên tổ chức thành kịch bản luồng khép kín (Flow Test: Thêm -> Sửa -> Kiểm tra -> Xoá) trong cùng một phương thức test để kiểm soát trọn vẹn vòng đời dữ liệu.

### 6.4 Phân loại nhãn kiểm thử (Test Label Taxonomy)
Hệ thống nhãn được đồng bộ hóa chính xác với mã nguồn `Groups.java` và cấu hình TestNG:
- **Nhãn mức chạy (Run Level)**:
  - `smoke`: Tập hợp các bài kiểm tra đường dẫn chính (Happy Path) độc lập, không phụ thuộc dữ liệu, chạy cực nhanh với thời gian dưới 3 phút. Kích hoạt ở mọi lần kiểm tra nhanh hoặc cổng tích hợp.
  - `regression`: Tập hợp kiểm thử hồi quy toàn diện bao phủ toàn bộ chức năng, trường hợp biên và kiểm thử âm tính của 6 phân hệ. Kích hoạt định kỳ hàng ngày.
  - `e2e`: Kịch bản tích hợp luồng nghiệp vụ dài liên kết toàn bộ các phân hệ từ Client đến Task. Chạy theo lịch định kỳ hoặc chế độ kiểm thử ban đêm.
- **Nhãn phân hệ (Module)**: `auth`, `dashboard`, `client`, `employee`, `project`, `task`.
- **Cờ hành vi (Flag)**:
  - `negative`: Đánh dấu các ca kiểm thử dữ liệu sai, ngoại lệ hoặc thông báo lỗi.
  - `dependent`: Đánh dấu các ca kiểm thử phụ thuộc dữ liệu; cấm tuyệt đối việc gắn cờ này vào bộ `smoke`.

### 6.5 Chạy song song và chính sách ổn định (Parallel Execution & Stability Policy)
- **Quản lý phiên đa luồng**: Sử dụng `ThreadLocal<WebDriver>` đảm bảo an toàn luồng (thread-safety) khi chạy song song.
- **Xử lý tương thích Java 25**: Khắc phục sự cố GraphOrchestrator NPE trên Java 25 (RSK-019) bằng cách cấu hình Maven Surefire chạy `<parallel>none</parallel>` ở mức build tổng thể. Tính năng chạy song song được kích hoạt có kiểm soát thông qua cấu hình TestNG XML Suite đối với các lớp kiểm thử hoàn toàn độc lập dữ liệu.
- **Chính sách kiểm soát Flaky Test**:
  - Ghi nhận và theo dõi các bài test có trạng thái không nhất quán thông qua `TestListener`.
  - Tách biệt (quarantine) các bài test chập chờn ra khỏi suite chính để phân tích nguyên nhân gốc (độ trễ mạng, sleep tĩnh hay thay đổi DOM).
  - Giới hạn cơ chế chạy lại (Retry): Chỉ cho phép chạy lại tối đa 1 lần thông qua `IRetryAnalyzer` đối với các ngoại lệ mạng hoặc timeout; nghiêm cấm dùng retry để lấp liếm lỗi logic hệ thống.
- **Chính sách chống "Xanh giả" (False Positive)**:
  - Thiết lập bắt buộc `<failIfNoTests>true</failIfNoTests>` trong cấu hình Maven Surefire.
  - Cấm sử dụng cấu hình bỏ qua lỗi `testFailureIgnore=true`. Bất kỳ lỗi kiểm thử nào xảy ra đều phải khiến tiến trình build báo FAILED ngay lập tức.

### 6.6 Tích hợp liên tục (CI/CD Integration)
- **Cổng kiểm tra Pull Request (PR Gate)**: Tự động kích hoạt bộ `smoke` khi có yêu cầu hợp nhất mã nguồn mới vào nhánh chính.
- **Kiểm thử định kỳ ban đêm (Nightly Regression)**: Kích hoạt toàn bộ bộ `regression` và `e2e` trên môi trường không đầu (Headless Mode) vào khung giờ thấp điểm.
- **Quản lý tệp kết quả (Artifact Archiving)**: Tự động thu thập thư mục báo cáo Allure, ExtentReports, ảnh chụp màn hình lỗi và video ghi hình sau mỗi phiên chạy.
- **Bảo mật biến môi trường**: Toàn bộ thông tin tài khoản và cấu hình nhạy cảm phải được truyền qua biến môi trường hoặc Secret Manager, không lưu trữ công khai trong kho mã nguồn.

### 6.7 Quy ước mã nguồn và quy trình rà soát (Code Conventions & Review)
- Quy ước đặt tên lớp kiểm thử: `*Test.java` (ví dụ: `LoginTest.java`, `ManageClientsTest.java`).
- Quy ước đặt tên phương thức kiểm thử: Mô tả hành động và tính năng rõ ràng theo tiền tố kiểm thử nghiệp vụ (ví dụ: phương thức kiểm thử đăng nhập admin thành công, phương thức kiểm thử thêm dự án mới chỉ định).
- Quy trình Code Review: Mọi thay đổi mã tự động hóa phải được rà soát bởi người thứ hai hoặc tự đối soát qua danh mục kiểm tra chất lượng trước khi đưa vào bộ kịch bản chính thức.

### 6.8 Báo cáo và hiển thị kết quả kiểm thử (Test Reporting)
- Tích hợp đồng thời hai hệ thống báo cáo:
  - **ExtentReports 5.1.2**: Cung cấp báo cáo HTML trực quan, xem offline thuận tiện với các bước ghi log chi tiết cho từng hành động giao diện.
  - **Allure TestNG 2.29.1**: Cung cấp phân tích thống kê chuyên sâu, phân loại nhóm lỗi, biểu đồ xu hướng lịch sử và đính kèm video quay màn hình bằng Monte Screen Recorder khi có lỗi xảy ra.

---

## 7. Môi trường và dữ liệu (Environment and Test Data)

### 7.1 Môi trường kiểm thử
- **Hệ thống mục tiêu**: Trang web demo công khai `https://hrm.anhtester.com/`.
- **Đặc thù môi trường**: Là môi trường đào tạo dùng chung cho cộng đồng; dữ liệu có thể bị thay đổi hoặc làm sạch bất ngờ bởi người dùng khác. Do đó, kiểm thử tự động phải luôn có bước xác thực môi trường trước khi chạy và kiểm soát dữ liệu độc lập.
- **Giới hạn an toàn**: Tuyệt đối không gửi tải lớn, không chạy quá 3 luồng đồng thời trên môi trường demo và không thực hiện các kịch bản phá hoại dữ liệu quản trị gốc.

### 7.2 Ma trận trình duyệt theo mức độ rủi ro
| Trình duyệt | Động cơ | Mức độ ưu tiên | Tỷ lệ bao phủ kịch bản | Ghi chú vận hành |
|---|---|:---:|:---:|---|
| Google Chrome | Chromium | Cao nhất (P1) | 100% kịch bản | Trình duyệt mục tiêu chính cho toàn bộ bộ kiểm thử smoke, regression và E2E |
| Microsoft Edge | Chromium | Ưu tiên 2 (P2) | 50% kịch bản hồi quy | Kiểm tra tính tương thích chéo trên nền tảng Chromium doanh nghiệp |
| Mozilla Firefox | Gecko | Ưu tiên 3 (P3) | Kịch bản khói chọn lọc | Kiểm tra tính tương thích giao diện trên động cơ phi Chromium |

### 7.3 Quản lý tài khoản kiểm thử
- **Tài khoản quản trị viên (Admin)**: Sử dụng tài khoản demo mặc định `admin_example` / `123456` được cấu hình tập trung trong `Configs.properties`. Tuyệt đối không lưu mật khẩu thực tế của tài khoản cá nhân.
- **Tài khoản khách hàng (Client)**: Được tạo tự động theo luồng kiểm thử nghiệp vụ với thông tin ngẫu nhiên và dọn dẹp sau khi kiểm tra xong.

### 7.4 Chiến lược dọn dẹp dữ liệu rác trên môi trường dùng chung
- Toàn bộ dữ liệu do kịch bản kiểm thử tự động tạo ra phải mang tiền tố nhận diện thống nhất `AUTOTEST_`.
- Xây dựng bộ kịch bản dọn dẹp định kỳ (Cleanup Suite) chuyên quét và xoá các bản ghi chứa tiền tố `AUTOTEST_` để giữ môi trường luôn sạch sẽ và giảm thiểu rủi ro tích tụ dữ liệu rác (RSK-026).

---

## 8. Quản lý lỗi (Defect Management)

### 8.1 Vòng đời của lỗi (Defect Lifecycle)
Quy trình xử lý lỗi tuân thủ vòng đời chuẩn:
1. **New (Mới phát hiện)**: Lỗi được phát hiện qua kiểm thử tự động hoặc kiểm thử thăm dò; tạo bản ghi lỗi kèm bằng chứng.
2. **Open (Xác nhận)**: QA Lead xác nhận lỗi hợp lệ, không phải do lỗi môi trường hay kịch bản kiểm thử.
3. **In Progress (Đang sửa)**: Phân công người chịu trách nhiệm khắc phục nguyên nhân gốc.
4. **Resolved (Đã sửa)**: Đã có bản vá hoặc cập nhật mã nguồn xử lý khiếm khuyết.
5. **Retest (Kiểm tra lại)**: Kích hoạt kịch bản kiểm thử tự động tương ứng để xác minh lỗi đã được khắc phục.
6. **Closed (Đóng lỗi)**: Ca kiểm thử xác nhận thành công; cập nhật trạng thái đóng lỗi.
7. **Reopened (Mở lại)**: Nếu lỗi vẫn tái diễn trong quá trình retest, lập tức chuyển về trạng thái mở lại.

### 8.2 Ma trận mức độ nghiêm trọng và mức độ ưu tiên
Ma trận kết hợp giữa Mức độ nghiêm trọng kỹ thuật (Severity) và Mức độ ưu tiên nghiệp vụ (Priority), kèm ví dụ thực tế trên hệ thống Anh Tester HRM:

| Severity \ Priority | Khẩn cấp (P1) | Cao (P2) | Vừa (P3) | Thấp (P4) |
|---|---|---|---|---|
| **Blocker (S1)** | Trang web HRM trả về lỗi 500/502 không truy cập được; tài khoản admin bị khóa không thể đăng nhập | Lỗi sập hệ thống khi mở màn hình Projects trên một trình duyệt cụ thể | *(Hiếm gặp)* | *(Không áp dụng)* |
| **Critical (S2)** | Lỗ hổng phân quyền Client truy cập trái phép URL quản trị của Admin (RSK-001) | Chức năng thêm dự án mới hoặc thêm khách hàng crash không lưu được dữ liệu | Chức năng xoá khách hàng không phản hồi trên giao diện | *(Không áp dụng)* |
| **Major (S3)** | Chức năng tải tệp đính kèm dự án chấp nhận file mã độc thực thi (RSK-010) | Tính toán sai lệch số giờ ngân sách dự án (RSK-012); ngày kết thúc cho phép trước ngày bắt đầu | Widget Dashboard hiển thị lệch số lượng dự án so với thực tế (RSK-003) | Lỗi hiển thị phân trang khi danh sách vượt quá 20 bản ghi |
| **Minor (S4)** | *(Không áp dụng)* | Sai thông báo cảnh báo lỗi trên form đăng nhập | Lệch khoảng cách menu điều hướng navbar trên màn hình nhỏ | Lỗi sai chính tả nhẹ trên tiêu đề trang |

### 8.3 Mẫu báo cáo lỗi chuẩn hóa (Bug Report Template)
- **Mã lỗi (Defect ID)**: `DEF-HRM-xxx`
- **Tiêu đề (Summary)**: Mô tả ngắn gọn sự cố kèm phân hệ (ví dụ: `[Project] Không chặn lưu khi End Date trước Start Date`).
- **Phân hệ (Module)**: `auth` | `client` | `project` | `task` | `employee` | `dashboard`.
- **Môi trường (Environment)**: URL demo, Trình duyệt (Chrome Version), Độ phân giải.
- **Mức độ (Severity / Priority)**: S1–S4 / P1–P4.
- **Các bước tái hiện (Steps to Reproduce)**: Trình bày chi tiết từng bước thao tác từ lúc đăng nhập đến khi phát sinh lỗi.
- **Kết quả thực tế (Actual Result)**: Hệ thống cho phép lưu thành công dự án với khoảng ngày không hợp lệ.
- **Kết quả mong đợi (Expected Result)**: Hệ thống phải hiển thị thông báo lỗi yêu cầu ngày kết thúc phải sau ngày bắt đầu.
- **Tệp đính kèm (Attachments)**: Ảnh chụp màn hình lỗi, video ghi hình và log chi tiết.

### 8.4 Quy tắc đóng lỗi và nhịp đối soát (Triage Rhythm)
- Các lỗi phát sinh được tổng hợp và đối soát định kỳ hàng tuần.
- Một lỗi chỉ được phép chuyển sang trạng thái Closed khi kịch bản kiểm thử tự động tương ứng đã chạy thành công ít nhất 2 lần liên tiếp trên môi trường kiểm thử.

---

## 9. Tiêu chí vào/ra và đình chỉ/tiếp tục (Entry/Exit and Suspension/Resumption Criteria)

Các tiêu chí mang tính nguyên tắc chiến lược, không gắn với ngày tháng cụ thể:

### 9.1 Tiêu chí bắt đầu kiểm thử (Entry Criteria)
- Môi trường kiểm thử `https://hrm.anhtester.com/` sẵn sàng kết nối, kịch bản kiểm tra sức khỏe đầu chu kỳ (Healthcheck) thực thi thành công.
- Mã nguồn kiểm thử đã được biên dịch thành công trên Maven, không có lỗi thư viện hay cú pháp.
- Dữ liệu kiểm thử trong file cấu hình và các tệp đính kèm mẫu đã sẵn sàng trong thư mục tài nguyên.

### 9.2 Tiêu chí kết thúc kiểm thử (Exit Criteria)
- 100% kịch bản trong bộ kiểm thử khói (`smoke`) thực thi thành công (Pass Rate 100%).
- Tối thiểu 95% kịch bản trong bộ kiểm thử hồi quy (`regression`) thực thi thành công.
- 100% các rủi ro mức Critical và High trong Sổ đăng ký rủi ro đã được kiểm thử và xác nhận có biện pháp kiểm soát hiệu quả.
- Không còn bất kỳ lỗi nào ở mức độ Blocker (S1) hoặc Critical (S2) ở trạng thái mở mà chưa có giải pháp thay thế.
- Báo cáo kiểm thử Allure và ExtentReports được trích xuất đầy đủ kèm biên bản nghiệm thu.

### 9.3 Tiêu chí đình chỉ kiểm thử (Suspension Criteria)
- Trang web mục tiêu gặp sự cố sập máy chủ kéo dài hoặc không thể kết nối mạng.
- Tài khoản quản trị demo bị đổi mật khẩu hoặc bị vô hiệu hóa, khiến toàn bộ kịch bản đăng nhập thất bại.
- Phát hiện lỗi nghiêm trọng ở mức độ kiến trúc framework khiến hơn 50% số lượng test case fail hàng loạt ngay khi khởi động trình duyệt.

### 9.4 Tiêu chí tiếp tục kiểm thử (Resumption Criteria)
- Đơn vị quản trị môi trường xác nhận sự cố máy chủ đã được khắc phục hoàn toàn.
- Tài khoản quản trị được khôi phục mật khẩu chuẩn và kịch bản Healthcheck đăng nhập thành công trở lại.
- Bản vá lỗi framework được cập nhật và kiểm chứng thành công trên một kịch bản đơn lẻ trước khi kích hoạt lại toàn bộ suite.

---

## 10. Chỉ số đo lường (Test Metrics and KPIs)

Bảng dưới đây quy định các chỉ số định lượng bắt buộc nhằm đánh giá hiệu quả chiến lược kiểm thử tự động. Mọi chỉ số đều có ngưỡng mục tiêu đề xuất kèm hành động khắc phục cụ thể:

| Tên chỉ số | Định nghĩa | Công thức | Nguồn dữ liệu | Mục tiêu [ĐỀ XUẤT] | Tần suất | Hành động nếu vượt ngưỡng |
|---|---|---|---|---|---|---|
| Độ phủ yêu cầu | Tỷ lệ các chức năng nghiệp vụ cốt lõi đã có kịch bản kiểm thử tự động | (Số chức năng có test / Tổng số chức năng trong phạm vi) × 100% | Ma trận truy vết RTM | [ĐỀ XUẤT: >= 90%] | Sau mỗi giai đoạn kiểm thử | Bổ sung kịch bản kiểm thử tự động cho các phân hệ còn thiếu độ bao phủ |
| Độ phủ rủi ro cao | Tỷ lệ các rủi ro High và Critical đã có kịch bản kiểm thử bao phủ | (Số rủi ro High & Critical có test / Tổng số rủi ro High & Critical) × 100% | Sổ đăng ký rủi ro (Risk Register) | [ĐỀ XUẤT: 100%] | Trước mỗi lần kích hoạt suite lớn | Lập tức thiết kế bổ sung kịch bản kiểm thử chuyên sâu cho rủi ro bị bỏ sót |
| Tỷ lệ ca kiểm thử đạt | Tỷ lệ các ca kiểm thử có kết quả thành công trên tổng số ca thực thi | (Số test case Pass / Tổng số test case thực thi) × 100% | Báo cáo Allure / ExtentReports | [ĐỀ XUẤT: >= 95% cho regression, 100% cho smoke] | Sau mỗi lần chạy suite | Dừng việc bàn giao kết quả; phân loại ngay lỗi do phần mềm hay do kịch bản để khắc phục |
| Tỷ lệ kiểm thử chập chờn | Tỷ lệ các ca kiểm thử thất thường (pass/fail xen kẽ mà không đổi mã) | (Số test case flaky / Tổng số test case thực thi) × 100% | Nhật ký Allure TestListener | [ĐỀ XUẤT: <= 2%] | Đánh giá định kỳ hàng tuần | Cách ly test case flaky khỏi suite chính; rà soát thay wait động và củng cố locator |
| Thời gian chạy suite khói | Tổng thời gian cần thiết để hoàn thành toàn bộ kịch bản trong smoke.xml | Thời gian kết thúc suite khói trừ thời gian bắt đầu | Nhật ký Maven Surefire | [ĐỀ XUẤT: <= 3 phút] | Sau mỗi lần chạy smoke | Rà soát loại bỏ các bước thao tác thừa và tối ưu hóa thời gian chờ đợi giao diện |
| Thời gian chạy suite hồi quy | Tổng thời gian cần thiết để hoàn thành toàn bộ kịch bản trong regression.xml | Thời gian kết thúc suite hồi quy trừ thời gian bắt đầu | Nhật ký Maven Surefire | [ĐỀ XUẤT: <= 15 phút] | Sau mỗi lần chạy regression | Tối ưu hóa dữ liệu kiểm thử và phân bổ kịch bản chạy song song đa luồng hợp lý |
| Tỷ lệ tự động hóa kịch bản | Tỷ lệ kịch bản đã được tự động hóa trên tổng số kịch bản kiểm thử thiết kế | (Số kịch bản tự động / Tổng số kịch bản kiểm thử được duyệt) × 100% | Sổ theo dõi ca kiểm thử | [ĐỀ XUẤT: >= 80%] | Đánh giá theo từng mốc giai đoạn | Ưu tiên nguồn lực viết mã tự động cho các ca kiểm thử hồi quy lặp lại nhiều lần |
| Thời gian phân loại lỗi | Thời gian trung bình từ lúc phát hiện test fail đến khi kết luận bug hay script lỗi | Tổng thời gian phân tích lỗi chia cho tổng số lỗi phát sinh | Bảng theo dõi phân loại lỗi | [ĐỀ XUẤT: <= 4 giờ] | Khi có ca kiểm thử thất bại | Nâng cao chất lượng ghi log chi tiết Log4j và đính kèm video quay màn hình tự động |
| Tỷ lệ lỗi mở lại | Tỷ lệ các lỗi đã thông báo sửa xong nhưng bị phát hiện vẫn còn lỗi khi retest | (Số lỗi bị Reopen / Tổng số lỗi đã Resolved) × 100% | Hệ thống theo dõi lỗi Defect Log | [ĐỀ XUẤT: <= 5%] | Sau mỗi chu kỳ retest lỗi | Yêu cầu đối soát kỹ lưỡng bản vá và tăng cường kịch bản kiểm tra xác nhận trước khi đóng |

---

## 11. Vai trò và trách nhiệm (Roles and Responsibilities - RACI)

Bảng ma trận RACI phân định rõ vai trò và trách nhiệm trong dự án kiểm thử tự động (R = Responsible, A = Accountable, C = Consulted, I = Informed):

| Hoạt động kiểm thử | Test Architect / QA Lead | Automation Tester | Mentor / Reviewer |
|---|:---:|:---:|:---:|
| Xây dựng và cập nhật Sổ đăng ký rủi ro (Risk Register) | **A / R** | **C** | **C / I** |
| Xây dựng Chiến lược kiểm thử tự động (Test Strategy) | **A / R** | **C** | **C / I** |
| Phê duyệt Chiến lược kiểm thử và các quyết định kiến trúc | **C** | **I** | **A** |
| Thiết kế kiến trúc Framework và chuẩn hóa Base / Utility | **A** | **R** | **C** |
| Thiết kế ca kiểm thử chi tiết và chuẩn bị dữ liệu Excel | **A** | **R** | **I** |
| Lập trình kịch bản kiểm thử tự động trên Page Object | **A** | **R** | **I** |
| Thực thi suite kiểm thử, phân loại và ghi nhận lỗi | **A** | **R** | **I** |
| Rà soát mã nguồn tự động hóa (Code Review) | **A** | **R** | **C** |
| Khắc phục lỗi kịch bản chập chờn (Flaky Tests) | **A** | **R** | **I** |
| Tổng hợp và trình bày báo cáo nghiệm thu kết quả đồ án | **A / R** | **R** | **A** |

> *Ghi chú về phân bổ nguồn lực*: Trong phạm vi đồ án cá nhân, vai trò Test Architect, QA Lead và Automation Tester do thành viên Võ Quỳnh Nhi đảm nhiệm chính; vai trò Mentor / Reviewer do Giảng viên hướng dẫn đảm nhận để phê duyệt và định hướng chất lượng.

---

## 12. Chính sách ứng dụng AI trong kiểm thử (AI Application Policy in Testing)

### 12.1 Ma trận phân định trách nhiệm người - máy theo giai đoạn
Chính sách quy định chi tiết vai trò của công cụ trí tuệ nhân tạo (AI) và người kiểm thử qua từng khâu trong quy trình kiểm thử:

| Giai đoạn kiểm thử | Công việc AI hỗ trợ thực hiện | Công việc con người bắt buộc thực hiện | Cổng kiểm soát con người |
|---|---|---|---|
| Phân tích bối cảnh & Rủi ro | Gợi ý các danh mục rủi ro tiềm ẩn, mẫu câu nguyên nhân - hậu quả chuẩn | Đối soát thực tế với giao diện hệ thống AUT, chấm điểm L và I dựa trên bối cảnh thật | Phê duyệt danh sách rủi ro chính thức trước khi đưa vào tài liệu |
| Thiết kế ca kiểm thử | Gợi ý các trường hợp kiểm thử biên, phân vùng tương đương và dữ liệu mẫu | Chọn lọc kịch bản phù hợp với mục tiêu đồ án, loại bỏ các ca kiểm thử phi thực tế | Rà soát 100% logic ca kiểm thử trước khi chuyển sang bước code |
| Chuẩn bị dữ liệu kiểm thử | Tạo danh sách dữ liệu mẫu ngẫu nhiên (tên, email, số điện thoại, mô tả) | Kiểm tra tính tương thích của dữ liệu với form nhập liệu trên hệ thống HRM | Xác thực dữ liệu không vi phạm chuẩn định dạng của ứng dụng |
| Viết mã kịch bản kiểm thử | Gợi ý khung mã nguồn Page Object, cú pháp TestNG và phương thức wrapper | Kiểm tra trực tiếp tính chính xác của locator trên DevTools, điều chỉnh logic chờ | Bắt buộc chạy thử thành công trên máy cục bộ trước khi merge mã |
| Thực thi & Xử lý Flaky | Gợi ý nguyên nhân tiềm ẩn khi bài test fail dựa trên thông báo ngoại lệ log | Điều tra môi trường mạng, kiểm tra trạng thái DOM thực tế và quyết định cách ly test | Không cho phép cơ chế tự sửa locator ngầm định mà không có sự đồng ý |
| Phân loại và báo cáo lỗi | Hỗ trợ soạn thảo tiêu đề và tóm tắt lỗi theo cấu trúc mẫu chuẩn hóa | Xác minh lỗi trực tiếp trên giao diện thực tế, chụp ảnh màn hình và đính kèm video | Con người ký duyệt báo cáo lỗi trước khi gửi cho bên liên quan |
| Tóm tắt kết quả kiểm thử | Tổng hợp số liệu thống kê từ log thực thi, hỗ trợ vẽ biểu đồ Markdown | Đánh giá mức độ đạt tiêu chuẩn nghiệm thu và đưa ra kết luận chiến lược | Kiểm tra chéo số liệu trong báo cáo với kết quả thực tế trên Allure |

### 12.2 Nguyên tắc cốt lõi về ứng dụng AI
1. **Cổng con người duyệt bắt buộc (Human-in-the-loop Gate)**: Mọi sản phẩm do mô hình AI tạo sinh gợi ý (từ tài liệu, ma trận kiểm thử đến mã tự động hóa) đều là bản thảo sơ bộ; bắt buộc phải có sự kiểm chứng, hiệu chỉnh và phê duyệt của con người trước khi lưu trữ chính thức vào kho lưu trữ mã nguồn.
2. **Bảo vệ tuyệt đối dữ liệu nhạy cảm và bí mật**: Nghiêm cấm đưa mật khẩu tài khoản thật, mã khóa định danh cá nhân, token dịch vụ bí mật hoặc dữ liệu doanh nghiệp riêng tư lên các mô hình ngôn ngữ lớn công cộng. Luôn sử dụng biến môi trường hoặc chuỗi giả định khi tạo prompt.
3. **Quản lý phiên bản câu lệnh mẫu (Prompt Versioning)**: Các mẫu prompt chất lượng cao dùng để sinh kịch bản kiểm thử, kiểm tra locator hoặc chuẩn hóa tài liệu phải được ghi chép và lưu trữ có cấu trúc để đảm bảo tính tái lập kết quả giữa các lần thực hiện.
4. **Cấm cơ chế tự sửa đổi âm thầm (No Silent Self-healing)**: Nghiêm cấm việc áp dụng các cơ chế AI tự động thay đổi locator hoặc nới lỏng điều kiện kiểm tra (soften assertions) mà không hiển thị cảnh báo rõ ràng cho con người kiểm soát.

### 12.3 Cách đo lường hiệu quả ứng dụng AI
Hiệu quả của việc ứng dụng AI trong quy trình kiểm thử được đo lường định lượng thông qua các chỉ số:
- **Tỷ lệ chấp nhận mã và tài liệu (Acceptance Rate)**: Đo bằng tỷ lệ phần trăm các dòng mã hoặc nội dung kịch bản do AI gợi ý được giữ lại sử dụng sau vòng rà soát của con người.
- **Tỷ lệ phát hiện ảo giác (Hallucination Rate)**: Đo bằng số lượng locator sai lệch hoặc kỳ vọng nghiệp vụ không có thực do AI tự suy diễn trên tổng số lượt gợi ý.
- **Thời gian tiết kiệm được (Time Saved Metric)**: So sánh thời gian thực tế để hoàn thành tài liệu kiểm thử và bộ khung kịch bản khi có AI hỗ trợ so với phương pháp thủ công truyền thống.

---

## 13. Công cụ và hạ tầng (Tools and Infrastructure)

Bảng tổng hợp công cụ và hạ tầng phục vụ chiến lược kiểm thử tự động, làm rõ công cụ sẵn có và công cụ đề xuất bổ sung:

| Mục đích sử dụng | Công cụ sẵn có trong dự án | Đề xuất bổ sung có căn cứ | Lý do lựa chọn và vai trò kỹ thuật |
|---|---|---|---|
| Ngôn ngữ lập trình chính | Java 25 | *(Duy trì)* | Ngôn ngữ hướng đối tượng tiêu chuẩn công nghiệp, hệ sinh thái kiểm thử phong phú, phù hợp kiến thức nền tảng của tác giả |
| Thư viện điều khiển trình duyệt | Selenium Java 4.31.0 | *(Duy trì)* | Chuẩn tự động hóa W3C phổ biến nhất toàn cầu, hỗ trợ mạnh mẽ đa trình duyệt, tương thích hoàn hảo với Java |
| Khung điều phối kiểm thử | TestNG 7.12.0 | *(Duy trì)* | Cung cấp cơ chế quản lý nhóm linh hoạt (smoke, regression), hỗ trợ tham số hóa đa dạng và cấu hình suite XML mạnh mẽ |
| Quản lý thư viện và tiến trình build | Apache Maven 3.10.0 | *(Duy trì)* | Quản lý vòng đời build chuẩn mực, tích hợp surefire plugin điều khiển luồng thực thi kiểm thử tự động |
| Quản lý dữ liệu kiểm thử | Apache POI 5.4.1 | *(Duy trì)* | Đọc và ghi dữ liệu kiểm thử từ bảng tính Excel (`LoginHRM.xlsx`), hỗ trợ kỹ thuật Data-Driven Testing |
| Báo cáo kiểm thử trực quan | ExtentReports 5.1.2 & Allure 2.29.1 | *(Duy trì)* | Kết hợp ExtentReports xem log nhanh offline và Allure phân tích biểu đồ xu hướng chuyên sâu |
| Ghi hình và chụp ảnh lỗi | Monte Screen Recorder & Commons IO | *(Duy trì)* | Tự động ghi video và lưu ảnh chụp màn hình khi có ca kiểm thử thất bại làm bằng chứng xác thực lỗi |
| Ghi nhật ký vận hành | Log4j 2.24.3 & SLF4J 2.0.17 | *(Duy trì)* | Xuất nhật ký hành động chi tiết phục vụ việc phân loại và gỡ lỗi kịch bản kiểm thử |
| Tự động hóa tích hợp liên tục | *(Chưa cấu hình)* | GitHub Actions (đề xuất bổ sung) | Tự động kích hoạt suite kiểm thử khói khi có Pull Request mới để bảo vệ chất lượng nhánh chính |
| Kiểm thử dịch vụ backend | *(Chưa có)* | RestAssured (đề xuất cho giai đoạn sau) | Mở rộng kiểm thử trực tiếp tầng API nhằm tăng tốc độ kiểm thử và giảm phụ thuộc vào giao diện UI |

> *Nguyên tắc nhất quán về công cụ*: Toàn bộ kịch bản tự động hóa chính được xây dựng trung thành trên nền tảng Selenium Java TestNG hiện có của repository. Đã loại trừ hoàn toàn các phương án công cụ ngoài stack hiện tại thay vì dùng làm công cụ chính để tránh phá vỡ kiến trúc mã nguồn và lãng phí công sức đã đầu tư.

---

## 14. Rủi ro của chiến lược và biện pháp giảm thiểu (Strategy Risks and Mitigation)

Bảng đối soát giữa các rủi ro chiến lược và các mã rủi ro tương ứng trong Sổ đăng ký rủi ro (`docs/01-risk-register.md`), kèm biện pháp xử lý:

| Mã rủi ro | Tóm tắt rủi ro đối với chiến lược | Mức độ | Biện pháp giảm thiểu trong chiến lược | Kế hoạch dự phòng chiến lược |
|:---:|---|:---:|---|---|
| RSK-023 | Môi trường demo chia sẻ bị đổi mật khẩu hoặc sập dịch vụ làm tê liệt suite | Critical | Thiết lập kịch bản Pre-flight Healthcheck kiểm tra kết nối và đăng nhập trước khi chạy suite chính | Chuyển sang tài khoản demo phụ hoặc liên hệ ban quản trị khôi phục mật khẩu chuẩn |
| RSK-017 | Kịch bản phụ thuộc dữ liệu cứng trong Excel gây gãy dây chuyền hàng loạt | High | Chuyển đổi toàn diện sang dữ liệu nguyên tử (Atomic Data), tự sinh chuỗi thời gian duy nhất | Chạy lại kịch bản đơn lẻ với dữ liệu độc lập mới hoặc nạp lại dữ liệu mẫu vào Excel |
| RSK-018 | Lạm dụng lệnh sleep() cứng làm kéo dài thời gian chạy và gây chập chờn | High | Loại bỏ toàn bộ lệnh sleep tĩnh; thay thế 100% bằng cơ chế WebDriverWait linh hoạt | Tạm thời tăng mức trần TIMEOUT trong file cấu hình khi mạng internet bị nghẽn |
| RSK-019 | Không tương thích giữa Java 25 và Maven Surefire gây lỗi crash khi chạy đa luồng | High | Cấu hình đơn luồng an toàn ở cấp độ Maven Surefire; chỉ bật song song ở mức TestNG Suite | Nghiên cứu hạ phiên bản JDK xuống Java 17/21 LTS nếu cần tối ưu thời gian chạy đa luồng |
| RSK-001 | Lỗ hổng phân quyền Client truy cập trái phép vào các URL quản trị của Admin | High | Thiết kế bộ ca kiểm thử bảo mật chuyên sâu kiểm tra phản hồi HTTP 403 Forbidden | Gửi cảnh báo lỗi bảo mật cho bên phát triển và đề xuất gắn middleware xác thực vai trò |
| RSK-010 | Chức năng tải tệp đính kèm dự án không kiểm tra mã độc và định dạng file | High | Kiểm thử tải lên các tệp tin giả định không hợp lệ (.exe, .bat, script) có kiểm soát | Chặn tải tệp lạ và cấu hình thư mục upload không có quyền thực thi mã nguồn |
| RSK-022 | Cấu hình pom.xml ngầm định gây ra hiện tượng "xanh giả" khi kiểm thử thất bại | Medium | Cấu hình bắt buộc failIfNoTests=true và cấm sử dụng testFailureIgnore trong build | Kiểm tra đối soát số lượng bài test thực thi thực tế trong báo cáo Allure sau mỗi lần chạy |
| RSK-014 | Thiếu tài liệu đặc tả yêu cầu chính thức dẫn tới hiểu sai hành vi phần mềm | High | Thực hiện kiểm thử thăm dò (Exploratory Testing) để xác lập hành vi chuẩn ban đầu | Thường xuyên đối soát và xin ý kiến giảng viên hướng dẫn về các điểm còn mơ hồ |

---

## 15. Giả định, ràng buộc và phụ thuộc (Assumptions, Constraints and Dependencies)

### 15.1 Giả định chiến lược
- [GIẢ ĐỊNH: Hệ thống trang web demo https://hrm.anhtester.com/ duy trì tính khả dụng ổn định trong suốt quá trình hoàn thiện và đánh giá đồ án].
- [GIẢ ĐỊNH: Trình duyệt Google Chrome phiên bản mới nhất là môi trường chuẩn mực được hội đồng đánh giá chấp nhận để kiểm chứng kết quả].
- [GIẢ ĐỊNH: Môi trường máy chấm bài hỗ trợ cấu hình runtime Java 25 và Maven phiên bản 3.6 trở lên].

### 15.2 Ràng buộc chiến lược
- Không có quyền truy cập trực tiếp vào cơ sở dữ liệu backend hoặc máy chủ hosting của hệ thống AUT; mọi thao tác chuẩn bị và dọn dẹp dữ liệu bắt buộc phải thực thi qua giao diện Web UI.
- Quỹ thời gian cá nhân của tác giả dành cho dự án kiểm thử ước tính khoảng 15–20 giờ làm việc mỗi tuần.
- Tuyệt đối không thực hiện các kịch bản kiểm thử gây nghẽn băng thông hoặc phá hoại dữ liệu quản trị trên hệ thống dùng chung.

### 15.3 Mối quan hệ phụ thuộc
- Phụ thuộc vào tính ổn định của đường truyền mạng Internet từ máy kiểm thử đến máy chủ demo `hrm.anhtester.com`.
- Phụ thuộc vào tính năng tự động tải phiên bản ChromeDriver tương thích của công cụ Selenium Manager.
- Phụ thuộc vào kết quả phân tích và tính ổn định của mã định danh rủi ro từ Sổ đăng ký rủi ro (`docs/01-risk-register.md`).

---

## 16. Truy vết ma trận kiểm thử (Traceability)

Chiến lược truy vết đảm bảo tính liên kết xuyên suốt hai chiều (Bi-directional Traceability) giữa rủi ro kinh doanh, quyết định chiến lược, ca kiểm thử và báo cáo lỗi:
1. **Truy vết từ Rủi ro sang Chiến lược**: Mỗi rủi ro mức độ Critical và High trong `docs/01-risk-register.md` được ánh xạ trực tiếp sang một mục tiêu kiểm thử và một phương pháp tiếp cận trong bảng tại Mục 4 và Mục 14.
2. **Truy vết từ Chiến lược sang Kế hoạch (Test Plan)**: Các quyết định kiến trúc (`STR-Dxx`) và các phân loại nhãn mức chạy (`smoke`, `regression`, `e2e`) là đầu vào trực tiếp để xây dựng lịch trình và phạm vi chi tiết trong tài liệu Test Plan kế tiếp.
3. **Quy chuẩn mã định danh thống nhất**:
   - Mã rủi ro: `RSK-NNN` (giữ nguyên không đánh số lại).
   - Mã quyết định chiến lược: `STR-Dxx` (cố định trong suốt vòng đời dự án).
   - Mã ca kiểm thử: Liên kết trực tiếp thông qua Ma trận truy vết yêu cầu (RTM) ở tài liệu thứ ba.

---

## 17. Nhật ký quyết định (Decision Log)

Bảng dưới đây ghi nhận toàn bộ các quyết định kiến trúc then chốt của chiến lược kiểm thử tự động, làm rõ lý do lựa chọn, các phương án đã loại trừ và sự đánh đổi kỹ thuật:

| ID | Quyết định | Lý do | Phương án đã loại | Đánh đổi | Trạng thái |
|:---:|---|---|---|---|:---:|
| STR-D01 | Duy trì nền tảng Selenium Java TestNG làm công nghệ kiểm thử tự động chính | Tận dụng mã nguồn hiện có, phù hợp kỹ năng của tác giả, đáp ứng trọn vẹn yêu cầu đồ án tốt nghiệp | Đã loại phương án chuyển đổi sang framework NodeJS hay ngôn ngữ khác vì đòi hỏi phải viết lại toàn bộ framework từ đầu | Tốc độ chạy có thể chậm hơn một số công cụ mới và cần quản lý driver chặt chẽ | Confirmed |
| STR-D02 | Áp dụng mô hình Page Object Model kết hợp WebUI Keyword Wrapper | Tách biệt hoàn toàn giữa cấu trúc giao diện và kịch bản kiểm thử, tối ưu khả năng tái sử dụng mã | Viết mã kiểm thử dạng kịch bản tuyến tính trực tiếp trong lớp test case | Tốn thời gian khởi tạo cấu trúc lớp ban đầu cho từng trang màn hình | Confirmed |
| STR-D03 | Loại bỏ triệt để lệnh sleep() tĩnh cứng và chuẩn hóa sang WebDriverWait | Giải quyết tận gốc nguyên nhân gây flaky test (RSK-018) và giảm thời gian chạy suite ít nhất 30% | Duy trì sleep tĩnh kết hợp tăng timeout chờ đợi của hệ thống | Phải rà soát và viết lại điều kiện chờ động cho từng phần tử giao diện | Confirmed |
| STR-D04 | Áp dụng chính sách dữ liệu nguyên tử độc lập kèm chuỗi thời gian duy nhất | Triệt tiêu hoàn toàn rủi ro gãy dây chuyền do phụ thuộc vào dòng cố định trong file Excel (RSK-017) | Sử dụng chung một tập dữ liệu cố định có sẵn trên trang web demo | Mỗi bài test tốn thêm thời gian khởi tạo dữ liệu mới và dọn dẹp sau khi chạy | Confirmed |
| STR-D05 | Cấu hình đơn luồng an toàn ở cấp độ Maven Surefire để khắc phục sự cố Java 25 | Ngăn ngừa triệt để lỗi crash GraphOrchestrator NPE trên Java 25 (RSK-019), đảm bảo build ổn định | Ép chạy song song đa luồng ở cấp độ Maven bất chấp nguy cơ lỗi crash | Tổng thời gian thực thi toàn bộ kịch bản theo tuần tự dài hơn so với chạy đa luồng | Confirmed |
| STR-D06 | Thiết lập cấu hình failIfNoTests=true và cấm bỏ qua lỗi kiểm thử trong build | Triệt tiêu hoàn toàn hiện tượng "xanh giả" (RSK-022), phản ánh trung thực chất lượng phần mềm | Cho phép build thành công ngay cả khi có ca kiểm thử thất bại | Tiến trình build sẽ bị dừng ngay lập tức khi phát hiện bất kỳ lỗi nào | Confirmed |
| STR-D07 | Bổ sung kịch bản kiểm tra sức khỏe đầu chu kỳ (Pre-flight Healthcheck) | Xác thực tính sẵn sàng của trang web demo và tài khoản admin trước khi kích hoạt suite lớn (RSK-023) | Kích hoạt trực tiếp toàn bộ suite kiểm thử hồi quy mà không kiểm tra trước | Tốn thêm một bước kiểm tra ngắn khoảng 30 giây trước mỗi lần chạy chính | Confirmed |
| STR-D08 | Tích hợp đồng thời hai hệ thống báo cáo Allure Report và ExtentReports | Tận dụng thế mạnh biểu đồ xu hướng của Allure và khả năng xem log nhanh offline của ExtentReports | Chỉ sử dụng báo cáo mặc định cơ bản không có biểu đồ của TestNG | Tăng dung lượng thư mục lưu trữ kết quả kiểm thử sau mỗi lần thực thi | Confirmed |
| STR-D09 | Áp dụng nguyên tắc con người duyệt bắt buộc cho toàn bộ mã do AI gợi ý | Ngăn chặn lỗi ảo giác locator và đảm bảo tác giả nắm vững bản chất kiến trúc framework (RSK-027, RSK-030) | Cho phép tự động hợp nhất mã do AI sinh ra trực tiếp vào kho lưu trữ | Tốn thêm thời gian rà soát và kiểm chứng thủ công của người kiểm thử | Confirmed |
| STR-D10 | Giới hạn cơ chế RetryAnalyzer chạy lại tối đa 1 lần chỉ dành cho lỗi mạng | Giảm thiểu tác động của mạng chập chờn (RSK-025) mà không che giấu lỗi logic thực tế của phần mềm | Cấu hình chạy lại nhiều lần (3 đến 5 lần) hoặc cấm hoàn toàn việc retry | Kéo dài thêm thời gian chạy đối với những bài test thực sự bị lỗi chức năng | Confirmed |

---

## 18. Thuật ngữ và tài liệu tham khảo (Glossary and References)

### 18.1 Thuật ngữ chuyên môn
- **AUT (Application Under Test)**: Hệ thống đang được kiểm thử (hệ thống Anh Tester HRM).
- **RBT (Risk-Based Testing)**: Phương pháp kiểm thử dựa trên đánh giá rủi ro để xác định mức độ ưu tiên và độ sâu kiểm thử.
- **POM (Page Object Model)**: Mẫu thiết kế phần mềm trong kiểm thử tự động, trừu tượng hóa các trang web thành các đối tượng lớp riêng biệt.
- **Flaky Test**: Ca kiểm thử có kết quả không ổn định (khi đạt khi không đạt) trên cùng một phiên bản mã nguồn mà không có sự thay đổi.
- **Atomic Data**: Dữ liệu kiểm thử độc lập, tự sinh và tự thu dọn trong từng bài test riêng biệt.
- **False Positive (Xanh giả)**: Trạng thái báo cáo kiểm thử hiển thị thành công nhưng trên thực tế bài test không được chạy hoặc lỗi bị bỏ qua.
- **Pre-flight Healthcheck**: Kịch bản kiểm tra nhanh tính sẵn sàng của môi trường và tài khoản trước khi kích hoạt toàn bộ suite kiểm thử chính.

### 18.2 Tài liệu tham khảo
1. ISTQB Certified Tester Foundation Level (CTFL) Syllabus Version 4.0.
2. ISTQB Advanced Level Test Manager & Test Automation Engineer Syllabus.
3. Tiêu chuẩn quốc tế ISO/IEC/IEEE 29119-3: Software Testing – Test Documentation.
4. Tiêu chuẩn quốc tế ISO/IEC 25010: Systems and software engineering – Systems and software Quality Requirements and Evaluation (SQRE).
5. Tài liệu hướng dẫn chính thức Selenium WebDriver (https://www.selenium.dev/documentation/).
6. Tài liệu hướng dẫn chính thức TestNG Framework (https://testng.org/doc/).

---

## 19. Xác nhận và tự kiểm tra chất lượng (Sign-off and Quality Checklist)

### 19.1 Điểm cần con người xác nhận
1. [CẦN XÁC NHẬN: Phê duyệt chính thức của Mentor hoặc Giảng viên hướng dẫn về 10 quyết định kiến trúc trong Decision Log (STR-D01 đến STR-D10)].
2. [CẦN XÁC NHẬN: Mức độ ưu tiên của việc thiết lập luồng tự động hóa CI/CD GitHub Actions trong giai đoạn hoàn thiện đồ án].
3. [CẦN XÁC NHẬN: Quyết định việc duy trì JDK 25 với chế độ chạy đơn luồng hay chuyển sang JDK 17/21 LTS để bật chạy song song đa luồng trên Maven Surefire].
4. [CẦN XÁC NHẬN: Thống nhất chỉ số mục tiêu thời gian thực thi của bộ kiểm thử hồi quy (đề xuất tối đa 15 phút)].

### 19.2 Danh mục tự kiểm tra chất lượng (Quality Gate Checklist)
- [x] Đầy đủ 19 mục bắt buộc theo đúng thứ tự quy chuẩn từ Mục 1 đến Mục 19.
- [x] Bảng Nhật ký quyết định (Decision Log) tại Mục 17 có đủ 10 quyết định chiến lược (`STR-D01` đến `STR-D10`) với đầy đủ Lý do, Phương án đã loại và Đánh đổi.
- [x] Không đề xuất các công cụ ngoài stack hiện tại làm công cụ kiểm thử chính; toàn bộ kịch bản tự động hóa trung thành 100% với nền tảng Selenium Java TestNG hiện có.
- [x] Mọi mục ngoài phạm vi (OUT-OF-SCOPE) đều có lý do kỹ thuật rõ ràng; mục tiêu SMART gắn chặt với các rủi ro cụ thể trong Risk Register.
- [x] Phân loại nhãn kiểm thử (`smoke`, `regression`, `e2e`) hoàn toàn trùng khớp với định nghĩa trong mã nguồn `Groups.java` và các file suite XML.
- [x] Bảng chỉ số đo lường (KPI) tại Mục 10 có đủ 9 chỉ số với đầy đủ công thức, nguồn dữ liệu, ngưỡng `[ĐỀ XUẤT]` và hành động cụ thể khi vượt ngưỡng.
- [x] Chính sách ứng dụng AI tại Mục 12 có bảng phân định trách nhiệm chi tiết, quy tắc cổng con người duyệt bắt buộc và các chỉ số đo lường hiệu quả.
- [x] Tài liệu tuân thủ tính ổn định lâu dài của Chiến lược: không rò rỉ ngày tháng cụ thể, không chia tuần/sprint và không chứa ID test case chi tiết.
- [x] Toàn bộ các giả định được gắn tag `[GIẢ ĐỊNH]`, các điểm chờ phê duyệt được gắn tag `[CẦN XÁC NHẬN]`, không chứa khung giữ chỗ trống chưa điền.
