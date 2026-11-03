# Test Risk Register – Sổ đăng ký rủi ro kiểm thử

## 1. Document Control

### 1.1 Thông tin tài liệu
- **Tên dự án**: Final Project Automation Test – Anh Tester HRM
- **Hệ thống được kiểm thử (AUT)**: Anh Tester HRM System (https://hrm.anhtester.com/)
- **Mã tài liệu**: TRR-HRM-01
- **Phiên bản**: v0.1
- **Trạng thái**: Draft
- **Tác giả**: Võ Quỳnh Nhi
- **Ngày tạo**: 01/11/2026
- **Ngày cập nhật**: 01/11/2026
- **Ngày rà soát**: [CẦN XÁC NHẬN]

### 1.2 Lịch sử thay đổi
| Phiên bản | Ngày | Tác giả | Tóm tắt thay đổi |
|---|---|---|---|
| v0.1 | 01/11/2026 | Võ Quỳnh Nhi | Khởi tạo Sổ đăng ký rủi ro kiểm thử (Risk Register) gồm 30 rủi ro bao phủ 5 danh mục và 6 phân hệ chức năng |

### 1.3 Tài liệu tham chiếu
- `docs/project-context.md`: Bối cảnh dự án kiểm thử tự động HRM
- `README.md` & `pom.xml`: Cấu hình kỹ thuật và thư viện kiểm thử tự động
- Chuẩn kiểm thử phần mềm quốc tế ISTQB CTFL & Advanced Level Test Manager (Risk-Based Testing)
- Tiêu chuẩn ISO/IEC/IEEE 29119-2 (Test Processes & Risk Management)

---

## 2. Phạm vi và phương pháp

### 2.1 Phạm vi nhận diện rủi ro
Sổ đăng ký rủi ro này nhận diện và định lượng các nguy cơ tiềm ẩn có thể ảnh hưởng đến chất lượng của hệ thống AUT (Anh Tester HRM) và sự thành công của dự án kiểm thử tự động (FinalProjectAutomationTest). Phạm vi rủi ro bao phủ 5 danh mục:
1. **PRODUCT**: Rủi ro về tính năng nghiệp vụ, phân quyền, toàn vẹn dữ liệu, xác thực, luồng tác vụ và upload tệp trên 6 phân hệ cốt lõi: `auth`, `dashboard`, `client`, `employee`, `project`, `task`.
2. **PROJECT**: Rủi ro về nguồn lực đơn lẻ, thiếu tài liệu đặc tả yêu cầu chính thức, thời hạn bàn giao đồ án và phụ thuộc vào hệ thống bên thứ ba.
3. **AUTOMATION**: Rủi ro về dữ liệu kiểm thử phụ thuộc dây chuyền, lạm dụng `sleep()` cứng gây flaky, xung đột Java 25 với Maven Surefire, locator DOM mong manh và bẫy "xanh giả".
4. **ENVIRONMENT**: Rủi ro từ môi trường demo dùng chung (dữ liệu bị sửa/xóa, đổi mật khẩu admin), xung đột phiên bản ChromeDriver, nghẽn mạng và dữ liệu rác tích tụ.
5. **AI-USAGE**: Rủi ro về ảo giác của mô hình ngôn ngữ lớn (hallucination), sơ suất lọt dữ liệu nhạy cảm vào prompt, tính không tiền định và nguy cơ phụ thuộc quá mức mà thiếu hiểu biết bản chất framework.

### 2.2 Phương pháp kiểm thử dựa trên rủi ro (Risk-Based Testing)
Quy trình đánh giá rủi ro tuân thủ các bước:
1. **Nhận diện (Identification)**: Mô tả rủi ro chuẩn hóa theo mẫu nguyên nhân - sự kiện - hậu quả (*"Do... có thể... dẫn tới..."*).
2. **Định lượng (Assessment)**: Chấm điểm Khả năng xảy ra (Likelihood - L) và Mức độ ảnh hưởng (Impact - I) theo thang điểm 1–5, kèm căn cứ thực tế từ bối cảnh dự án.
3. **Phân loại (Classification)**: Tính điểm rủi ro $Score = L \times I$ để xếp loại mức độ: *Low*, *Medium*, *High*, *Critical*.
4. **Đối phó (Treatment)**: Xác định chiến lược đối phó (*Avoid, Mitigate, Transfer, Accept*), hành động giảm thiểu cụ thể, kế hoạch dự phòng (*Contingency*) và dấu hiệu cảnh báo sớm (*Trigger*).
5. **Ánh xạ kiểm thử (Test Mapping)**: Dựa vào điểm số rủi ro để phân bổ kỹ thuật thiết kế test case, độ sâu kiểm thử (*Depth: Extensive / Balanced / Opportunity-based / Report-only*), mức chạy (*Run label: smoke / regression / e2e*) và tiêu chí hoàn thành (*Done criteria*).

---

## 3. Thang đánh giá

### 3.1 Thang điểm Khả năng xảy ra (Likelihood - L)
| Điểm | Mức độ | Định nghĩa |
|:---:|---|---|
| **1** | Hiếm gặp | Hầu như không xảy ra trong điều kiện vận hành bình thường; cần sự kết hợp của nhiều điều kiện hiếm hoi. |
| **2** | Ít khả năng | Có thể xảy ra nhưng tần suất rất thấp; xuất hiện trong các luồng biên hoặc cấu hình đặc biệt. |
| **3** | Có thể | Có khả năng xảy ra trong quá trình sử dụng thông thường hoặc kiểm thử định kỳ. |
| **4** | Khả năng cao | Thường xuyên xuất hiện hoặc đã từng quan sát thấy dấu hiệu trên môi trường thử nghiệm. |
| **5** | Gần như chắc chắn | Chắc chắn xảy ra nếu không có biện pháp ngăn chặn chủ động; lỗi cố hữu của kiến trúc/môi trường. |

### 3.2 Thang điểm Mức độ ảnh hưởng (Impact - I)
| Điểm | Mức độ | Định nghĩa |
|:---:|---|---|
| **1** | Không đáng kể | Lỗi giao diện nhỏ, chính tả hoặc lệch vài pixel; không ảnh hưởng chức năng nghiệp vụ. |
| **2** | Nhỏ | Ảnh hưởng nhỏ đến trải nghiệm người dùng; có phương án thay thế dễ dàng; không mất dữ liệu. |
| **3** | Vừa | Chức năng phụ bị gián đoạn hoặc dữ liệu hiển thị sai lệch tạm thời; cần thao tác thủ công để xử lý. |
| **4** | Lớn | Chức năng nghiệp vụ chính bị lỗi; gây hỏng dữ liệu hoặc chặn luồng kiểm thử tự động diện rộng. |
| **5** | Nghiêm trọng | Toàn bộ hệ thống sập; mất dữ liệu vĩnh viễn; rò rỉ bảo mật cấp độ cao; suite kiểm thử tê liệt hoàn toàn. |

### 3.3 Ma trận phân loại mức rủi ro (Risk Matrix)
Điểm rủi ro được tính theo công thức: $\text{Score} = L \times I$ (thang điểm từ 1 đến 25).

| Score ($L \times I$) | Mức rủi ro (Level) | Hướng xử lý kiểm thử (Testing Guideline) |
|:---:|:---:|---|
| **1 – 4** | **Low** | Độ sâu kiểm thử tùy chọn (*Opportunity-based*); kiểm tra nhanh khi có thời gian dư. |
| **5 – 9** | **Medium** | Độ sâu cân bằng (*Balanced*); bao phủ bằng kiểm thử phân vùng tương đương và giá trị biên; đưa vào suite `regression`. |
| **10 – 16** | **High** | Độ sâu chuyên sâu (*Extensive*) hoặc cân bằng (*Balanced*); bắt buộc tự động hóa; có kế hoạch dự phòng và kích hoạt sớm. |
| **20 – 25** | **Critical** | Độ sâu tối đa (*Extensive*); ưu tiên tuyệt đối trong suite `smoke` và `e2e`; giám sát liên tục ở mọi đợt thực thi. |

---

## 4. Risk Register

Bảng dưới đây là sổ đăng ký toàn diện 30 rủi ro của dự án kiểm thử tự động HRM:

| ID | Category | Module | Description | L | L reason | I | I reason | Score | Level | Strategy | Mitigation | Contingency | Trigger | Owner | Residual L | Residual I | Residual score | Status |
|---|---|---|---|:---:|---|:---:|---|:---:|:---:|---|---|---|---|---|:---:|:---:|:---:|:---:|
| RSK-001 | PRODUCT | auth | Do hệ thống phân quyền không thu hồi token phiên cũ khi đổi vai trò, có thể xảy ra tình trạng tài khoản Client truy cập trái phép vào các trang quản trị của Admin, dẫn tới nguy cơ lộ dữ liệu mật của toàn bộ doanh nghiệp. | 3 | Cơ chế session của web demo cơ bản ít khi invalidate triệt để khi chuyển đổi vai trò | 5 | Rò rỉ thông tin lương, nhân sự, dự án mật của toàn bộ tổ chức và vi phạm bảo mật nghiêm trọng | 15 | High | Mitigate | Thiết kế bộ test case kiểm thử phân quyền chéo, xác minh Client không thể truy cập URL /erp/* của Admin | Khóa tạm thời các quyền truy cập cấp cao của Client và bổ sung middleware kiểm tra vai trò tại backend | Phát hiện HTTP 200 thay vì 403 khi dùng session Client gọi trực tiếp URL quản trị | QA Lead / Tester | 1 | 4 | 4 | Open |
| RSK-002 | PRODUCT | auth | Do form đăng nhập không giới hạn số lần thử mật khẩu sai, có thể xảy ra tấn công brute-force dò mật khẩu tài khoản người dùng, dẫn tới tài khoản bị chiếm đoạt và dữ liệu bị giả mạo. | 3 | Trang login của HRM demo không có captcha hay lockout policy sau nhiều lần nhập sai liên tiếp | 4 | Kẻ gian chiếm quyền điều khiển tài khoản quản trị viên và can thiệp toàn bộ hệ thống | 12 | High | Mitigate | Kiểm thử kịch bản gửi liên tiếp 10 lần sai mật khẩu để kiểm tra thông báo cảnh báo hoặc cơ chế khóa tạm thời | Gửi báo cáo lỗi bảo mật cho bên phát triển đề xuất áp dụng rate limiting hoặc CAPTCHA sau 5 lần nhập sai | Tài khoản admin liên tục đăng nhập thất bại với số lượng lớn trong thời gian ngắn | Tester | 2 | 3 | 6 | Open |
| RSK-003 | PRODUCT | dashboard | Do các widget thống kê trên Dashboard truy vấn dữ liệu bất đồng bộ không đồng bộ hóa kịp, có thể xảy ra sai lệch số lượng dự án và nhân sự hiển thị so với dữ liệu thực tế, dẫn tới quyết định điều hành quản trị viên bị sai sót. | 3 | Dashboard tổng hợp số liệu từ nhiều bảng và phụ thuộc vào AJAX tải chậm | 3 | Nhà quản trị nhìn nhận sai tiến độ dự án và phân bổ nguồn lực không chính xác | 9 | Medium | Mitigate | Xây dựng kịch bản kiểm thử đối chiếu số liệu widget Dashboard với danh sách thực tế trong Projects và Employees | Tải lại trang Dashboard hoặc kiểm tra dữ liệu nguồn tại trang chi tiết phân hệ tương ứng | Số lượng project trên widget lệch so với tổng số hàng đếm được trong bảng dự án | Tester | 2 | 2 | 4 | Open |
| RSK-004 | PRODUCT | dashboard | Do thanh điều hướng navbar không xử lý tốt trên các độ phân giải màn hình khác nhau, có thể xảy ra tình trạng menu bị ẩn hoặc tràn layout, dẫn tới người dùng không thể bấm chọn các phân hệ chức năng. | 2 | Giao diện web dùng template chuẩn nên lỗi tràn menu hiếm khi xảy ra trên màn hình thông dụng | 2 | Chỉ ảnh hưởng tạm thời tới hiển thị giao diện, người dùng vẫn có thể thu phóng hoặc xoay màn hình | 4 | Low | Mitigate | Kiểm thử responsive UI navigation bar trên 3 độ phân giải tiêu chuẩn (1920x1080, 1366x768, 768x1024) | Dùng phím tắt hoặc nhập trực tiếp đường dẫn URL của phân hệ để tiếp tục làm việc | Phát hiện phần tử menu bị che khuất hoặc thanh navbar không phản hồi sự kiện click | Tester | 1 | 2 | 2 | Open |
| RSK-005 | PRODUCT | client | Do hệ thống không kiểm tra tính duy nhất của Username và Email khi tạo Client, có thể xảy ra tình trạng trùng lặp thông tin định danh khách hàng, dẫn tới lỗi xung đột dữ liệu và không thể phân biệt tài khoản khi đăng nhập. | 4 | Trang thêm client thường xuyên cho phép nhập dữ liệu tự do nếu backend thiếu unique constraint | 4 | Làm hỏng quy trình xác thực và gây nhầm lẫn hồ sơ thanh toán, hợp đồng của khách hàng | 16 | High | Mitigate | Thiết kế test case kiểm tra biên và trùng lặp cho Email và Username của Client với thông báo lỗi rõ ràng | Xóa thủ công bản ghi khách hàng bị trùng lặp và liên hệ đội dev bổ sung ràng buộc unique trên database | Thông báo tạo client thành công hiển thị dù email đã tồn tại trong danh sách | QA Lead / Tester | 2 | 3 | 6 | Open |
| RSK-006 | PRODUCT | client | Do chức năng xoá Client thực hiện cơ chế xóa cứng mà không kiểm tra ràng buộc khóa ngoại, có thể xảy ra lỗi mất toàn bộ dữ liệu dự án và hợp đồng liên quan đến khách hàng đó, dẫn tới mất dấu vết lịch sử nghiệp vụ. | 3 | Ứng dụng demo cho phép bấm delete trực tiếp trên bảng mà không có cảnh báo ràng buộc khóa ngoại | 4 | Mất sạch dữ liệu dự án, thanh toán và tác vụ gắn liền với khách hàng đã bị xoá | 12 | High | Mitigate | Kiểm thử kiểm tra ràng buộc toàn vẹn khi xóa Client đang có Project liên kết (phải chặn hoặc inactive) | Khôi phục dữ liệu từ bản sao lưu hoặc ngừng cho phép xóa trực tiếp trên giao diện | Xóa thành công Client nhưng các Project trực thuộc biến mất hoặc báo lỗi 500 khi mở | Tester | 1 | 4 | 4 | Open |
| RSK-007 | PRODUCT | employee | Do chức năng tạo nhân viên mới không validate chặt chẽ định dạng số điện thoại và email, có thể xảy ra tình trạng lưu dữ liệu rác không hợp lệ vào hệ thống, dẫn tới việc gửi thông báo và liên lạc nội bộ thất bại hoàn toàn. | 2 | Hầu hết các ca nhập liệu thông thường đều dùng dữ liệu mẫu chuẩn từ file Excel | 2 | Chỉ ảnh hưởng đến chất lượng dữ liệu hiển thị, không làm gián đoạn luồng nghiệp vụ chính | 4 | Low | Mitigate | Áp dụng kỹ thuật Phân vùng tương đương và Phân tích giá trị biên để kiểm thử các định dạng email/phone sai | Yêu cầu chỉnh sửa lại thông tin nhân sự thông qua chức năng Edit Employee | Bản ghi nhân viên lưu thành công với số điện thoại chứa ký tự chữ hoặc email thiếu đuôi tên miền | Tester | 1 | 2 | 2 | Open |
| RSK-008 | PRODUCT | employee | Do hệ thống cho phép gán nhân viên đã bị vô hiệu hóa hoặc đã nghỉ việc vào dự án mới, có thể xảy ra việc phân công sai nguồn lực thực thi, dẫn tới dự án bị đình trệ và sai lệch báo cáo nhân sự. | 2 | Số lượng nhân sự demo ít nên việc chọn nhầm nhân sự inactive ít khi diễn ra | 2 | Chỉ cần gán lại nhân sự khác mà không làm hỏng cấu trúc dữ liệu dự án | 4 | Low | Mitigate | Thực hiện kiểm thử phân bổ nguồn lực: chỉ nhân sự có trạng thái Active mới xuất hiện trong danh sách gán | Gỡ bỏ nhân viên không hợp lệ ra khỏi dự án và gán nhân sự thay thế ngay lập tức | Dropdown chọn nhân sự xuất hiện các tài khoản nhân viên đã có trạng thái Inactive | Tester | 1 | 2 | 2 | Open |
| RSK-009 | PRODUCT | project | Do trường ngày tháng bắt đầu và kết thúc của dự án không kiểm tra logic thứ tự thời gian, có thể xảy ra tình trạng ngày kết thúc trước ngày bắt đầu, dẫn tới sai lệch biểu đồ tiến độ và thuật toán tính giờ ngân sách bị lỗi. | 3 | Datepicker trên giao diện web thường cho phép người dùng tự nhập hoặc chọn ngày không ràng buộc | 3 | Gây lỗi hiển thị biểu đồ Gantt và tính toán sai chi phí ngân sách dự án | 9 | Medium | Mitigate | Kiểm thử kịch bản chọn End Date trước Start Date, xác nhận hệ thống hiển thị thông báo lỗi chặn lưu | Chỉnh sửa lại khoảng ngày hợp lệ qua chức năng cập nhật thông tin dự án | Dự án lưu thành công với End Date nhỏ hơn Start Date trên màn hình danh sách | Tester | 1 | 2 | 2 | Open |
| RSK-010 | PRODUCT | project | Do chức năng tải tệp đính kèm dự án không kiểm tra phần mở rộng và mã độc MIME type, có thể xảy ra việc người dùng tải lên tệp tin độc hại (.exe, .php, .sh), dẫn tới nguy cơ bị tấn công thực thi mã từ xa trên máy chủ. | 3 | Form upload file của dự án demo thường thiếu whitelist định dạng tệp phía server | 5 | Máy chủ có thể bị chiếm quyền điều khiển và rò rỉ toàn bộ cơ sở dữ liệu | 15 | High | Mitigate | Kiểm thử tải lên các tệp tin giả định không hợp lệ (.exe, .bat, .svg chứa script) ở mức độ an toàn | Xóa ngay tệp tải lên đáng ngờ và cấu hình chặn thực thi mã trong thư mục upload của web server | Hệ thống chấp nhận lưu tệp đính kèm có đuôi mở rộng nằm ngoài danh sách tài liệu cho phép | QA Lead / Tester | 1 | 4 | 4 | Open |
| RSK-011 | PRODUCT | task | Do trạng thái công việc chuyển đổi bất hợp lý mà không tuân theo quy trình vòng đời nghiệp vụ, có thể xảy ra việc Task đã đóng vẫn cho phép cập nhật giờ làm việc, dẫn tới sai lệch dữ liệu thanh toán công chuẩn. | 3 | Hệ thống dropdown status cho phép chọn tự do mọi trạng thái mà không kiểm tra điều kiện tiên quyết | 3 | Gây sai lệch báo cáo nghiệm thu công việc và khiếu nại tài chính từ khách hàng | 9 | Medium | Mitigate | Kiểm thử kỹ thuật Bảng chuyển trạng thái (State Transition Testing) cho toàn bộ vòng đời tác vụ | Khóa quyền cập nhật dữ liệu của Task sau khi trạng thái hoàn thành đã được xác nhận | Cho phép chỉnh sửa giờ làm việc hoặc thêm bình luận mới khi task đang ở trạng thái Completed/Closed | Tester | 1 | 2 | 2 | Open |
| RSK-012 | PRODUCT | task | Do trường số giờ ngân sách công việc không giới hạn giá trị âm hoặc ký tự đặc biệt, có thể xảy ra lỗi tính tổng giờ dự án bị âm, dẫn tới báo cáo chi phí tổng thể bị sai lệch nghiêm trọng. | 3 | Input số thường chỉ dùng type text cơ bản mà không kiểm tra giá trị lớn hơn 0 trên form | 3 | Số liệu tổng kết ngân sách dự án bị âm gây hiểu lầm trong việc quản trị tài chính | 9 | Medium | Mitigate | Kiểm thử giá trị biên và tương đương với các giá trị: số âm (-1), số 0, chuỗi chữ cái, ký tự đặc biệt | Chỉnh sửa số giờ về giá trị dương hợp lệ qua chức năng cập nhật tác vụ | Hệ thống lưu thành công task có số giờ là số âm hoặc hiển thị NaN trên bảng thống kê | Tester | 1 | 2 | 2 | Open |
| RSK-013 | PROJECT | general | Do dự án chỉ có 1 nhân sự phụ trách toàn bộ kịch bản tự động hóa, có thể xảy ra đình trệ tiến độ khi nhân sự bị ốm hoặc gặp sự cố bất khả kháng, dẫn tới không đạt mốc hoàn thành đồ án theo kế hoạch. | 3 | Toàn bộ công việc thiết kế, code, bảo trì do duy nhất 1 tester Võ Quỳnh Nhi thực hiện | 4 | Mọi hoạt động kiểm thử bị dừng hoàn toàn và nguy cơ trễ hạn nộp đồ án tốt nghiệp | 12 | High | Mitigate | Lập tài liệu hướng dẫn bàn giao chi tiết, viết script chuẩn hóa và lưu mã nguồn rõ ràng trên Git repo | Ưu tiên hoàn thiện trước các luồng smoke test then chốt và xin gia hạn thời gian nộp bài nếu có sự cố | Tiến độ hoàn thành kịch bản trễ quá 3 ngày so với mốc kế hoạch tuần | Project Manager / Mentor | 2 | 3 | 6 | Open |
| RSK-014 | PROJECT | general | Do thiếu tài liệu đặc tả yêu cầu chính thức của ứng dụng HRM, có thể xảy ra việc hiểu sai hành vi mong đợi của phần mềm, dẫn tới viết kịch bản kiểm thử sai tiêu chí nghiệm thu. | 4 | AUT là trang demo bên ngoài, không có tài liệu kỹ thuật chi tiết đi kèm | 3 | Phải sửa lại nhiều kịch bản test case và mất thời gian đối chiếu lại nghiệp vụ | 12 | High | Mitigate | Thực hiện kiểm thử thăm dò (Exploratory Testing) ghi chép lại hành vi chuẩn của hệ thống trước khi code | Tổ chức đối soát định kỳ với giảng viên hướng dẫn hoặc tài liệu hướng dẫn khóa học | Kịch bản kiểm thử liên tục fail vì kết quả thực tế khác với suy đoán chủ quan | QA Lead / Tester | 2 | 2 | 4 | Open |
| RSK-015 | PROJECT | general | Do thời gian thực hiện dự án bị giới hạn theo lịch bảo vệ đồ án, có thể xảy ra việc không đủ thời gian bao phủ toàn bộ các luồng phụ và kiểm thử biên, dẫn tới độ bao phủ kiểm thử không đạt kỳ vọng ban đầu. | 3 | Quỹ thời gian làm việc chỉ khoảng 15-20h/tuần cùng với nhiều môn học/công việc khác | 3 | Không kịp hoàn thành các bộ test nâng cao và giảm chất lượng tổng thể của đồ án | 9 | Medium | Mitigate | Áp dụng phương pháp kiểm thử dựa trên rủi ro, ưu tiên tuyệt đối cho Smoke và Top rủi ro cao | Cắt giảm phạm vi các module ít quan trọng, tập trung hoàn thiện module Auth, Client, Project | Khối lượng công việc tồn đọng vượt quá 40% khi chu kỳ đã đi qua một nửa thời gian | Tester | 2 | 2 | 4 | Open |
| RSK-016 | PROJECT | general | Do thiếu môi trường Staging/UAT riêng biệt và phụ thuộc hoàn toàn vào website bên thứ ba, có thể xảy ra tình trạng trang web bị bảo trì hoặc đổi domain bất ngờ, dẫn tới toàn bộ hoạt động kiểm thử bị tê liệt. | 2 | Site demo của cộng đồng đào tạo có thể được cập nhật hoặc đóng đột ngột không báo trước | 5 | Không thể thực thi bất kỳ kịch bản automation nào và mất phương tiện demo trực tiếp | 10 | High | Mitigate | Ghi lại video bằng chứng (Monte Recorder) và lưu trữ kết quả Allure/Extent report offline sau mỗi đợt chạy | Chuẩn bị bản mock server cục bộ hoặc kịch bản chạy offline với dữ liệu đã lưu sẵn | Website https://hrm.anhtester.com/ trả về mã lỗi 502, 503 hoặc không thể kết nối quá 2 giờ | QA Lead / Tester | 1 | 4 | 4 | Open |
| RSK-017 | AUTOMATION | general | Do các kịch bản kiểm thử phụ thuộc dữ liệu tuần tự và số hàng cố định trong file Excel, có thể xảy ra lỗi dây chuyền khi một test case trước đó thất bại hoặc xóa mất dữ liệu, dẫn tới toàn bộ các test case dependent phía sau đều gãy hàng loạt. | 4 | Rất nhiều test trong ManageClientsTest và ProjectsPageTest có cờ Groups.DEPENDENT và dùng row=2 cố định | 4 | Báo cáo kết quả thất bại diện rộng dù nguyên nhân gốc chỉ do 1 test ban đầu gây ra | 16 | High | Mitigate | Tái cấu trúc test case theo nguyên tắc độc lập dữ liệu: mỗi test tự tạo dữ liệu unique timestamp và tự dọn dẹp | Chạy lại kịch bản riêng lẻ với dòng dữ liệu mới trong file Excel hoặc khởi tạo lại dữ liệu mẫu | Khi TC_AddNewClient fail thì TC_EditClient và TC_DeleteClient cũng lập tức fail theo | Automation Tester | 2 | 3 | 6 | Open |
| RSK-018 | AUTOMATION | general | Do mã kiểm thử lạm dụng nhiều lệnh dừng tĩnh sleep() cứng từ 1 đến 9 giây, có thể xảy ra tình trạng suite chạy quá chậm hoặc phát sinh lỗi flaky khi mạng chập chờn, dẫn tới kết quả kiểm thử không đáng tin cậy. | 4 | Trong DashboardPage có sleep(9), ProjectsPage có nhiều sleep(5) và sleep(4) | 3 | Kéo dài thời gian chạy suite kiểm thử lên nhiều lần và gây timeout bất thường khi mạng lag | 12 | High | Mitigate | Thay thế toàn bộ lệnh sleep() cứng bằng WebDriverWait linh hoạt (waitForElementVisible, waitForElementClickable) | Tạm thời tăng TIMEOUT trong Configs.properties nếu môi trường mạng đột ngột bị chậm | Thời gian thực thi suite vượt quá 15 phút hoặc có test fail vì ElementNotInteractableException | Automation Tester | 1 | 2 | 2 | Open |
| RSK-019 | AUTOMATION | general | Do sự không tương thích giữa Java 25 và Maven Surefire Plugin gây lỗi GraphOrchestrator NullPointerException khi chạy song song, có thể xảy ra việc build bị crash đột ngột, dẫn tới không thể tối ưu hóa thời gian thực thi bằng đa luồng. | 4 | pom.xml đã phải ghi chú Force Single-Threaded và đặt parallel=none do lỗi này trên Java 25 | 3 | Không tận dụng được tài nguyên CPU đa nhân và thời gian chạy toàn bộ kịch bản bị kéo dài | 12 | High | Mitigate | Nghiên cứu hạ phiên bản JDK xuống Java 17/21 LTS hoặc nâng cấp Surefire plugin lên phiên bản có bản vá | Duy trì cấu hình single-thread an toàn tại pom.xml và chỉ chạy song song ở mức TestNG suite độc lập | Maven ném ngoại lệ java.lang.NullPointerException tại GraphOrchestrator khi bật parallel | Automation Tester | 2 | 2 | 4 | Open |
| RSK-020 | AUTOMATION | general | Do locator XPath được viết theo dạng chỉ mục tuyệt đối và phụ thuộc cấu trúc DOM mong manh, có thể xảy ra tình trạng locator bị gãy khi ứng dụng thay đổi giao diện nhỏ, dẫn tới chi phí bảo trì script tăng cao. | 3 | Mã nguồn tồn tại các locator dạng nối chuỗi phức tạp như ((//input/parent::div)/parent::div)/following-sibling::div... | 3 | Nhiều test case đồng loạt fail vì NoSuchElementException dù chức năng phần mềm vẫn hoạt động bình thường | 9 | Medium | Mitigate | Refactor các locator sang thuộc tính ngữ nghĩa ổn định (ID, name, unique CSS class, text normalize-space) | Cập nhật nhanh locator trong các file Page Object khi phát hiện DOM thay đổi | Test case fail hàng loạt với lỗi TimeoutException hoặc NoSuchElementException sau một bản cập nhật | Automation Tester | 1 | 2 | 2 | Open |
| RSK-021 | AUTOMATION | general | Do kịch bản kiểm thử tải tệp đính kèm sử dụng đường dẫn tệp tin cục bộ cố định, có thể xảy ra lỗi không tìm thấy tệp khi thực thi trên máy khác hoặc môi trường CI, dẫn tới test case upload tệp bị thất bại. | 3 | Đường dẫn tệp ảnh và tài liệu mẫu đang phụ thuộc vào cấu trúc thư mục máy phát triển | 3 | Gây lỗi cho toàn bộ kịch bản upload tệp trong ProjectsPageTest và E2E Flow | 9 | Medium | Mitigate | Sử dụng đường dẫn tương đối thông qua SystemHelpers.getCurrentDir() kết hợp tài nguyên trong src/test/resources | Sao chép các file tài liệu mẫu vào đúng thư mục đích được cấu hình trước khi chạy suite | Ném ngoại lệ FileNotFoundException hoặc IllegalArgumentException trong bước addAttachFiles | Automation Tester | 1 | 2 | 2 | Open |
| RSK-022 | AUTOMATION | general | Do cấu hình test ngầm định trong pom.xml thiết lập failIfNoTests là false và có thể bỏ qua lỗi, có thể xảy ra tình trạng "xanh giả" khi build vẫn báo thành công dù không có test nào được chạy, dẫn tới che giấu khiếm khuyết phần mềm. | 2 | File pom.xml cấu hình failIfNoTests=false và từng có cấu hình testFailureIgnore=true bị comment | 4 | Người đánh giá hoặc CI lầm tưởng toàn bộ hệ thống đạt chất lượng trong khi bài kiểm thử không thực thi | 8 | Medium | Avoid | Cấu hình chặt chẽ failIfNoTests=true và đảm bảo build fail ngay lập tức khi có bất kỳ test case nào thất bại | Kiểm tra số lượng test thực thi thực tế trong file Allure report và Surefire report sau mỗi lần chạy | Build báo SUCCESS nhưng thời gian chạy dưới 5 giây và số lượng test executed bằng 0 | Automation Tester | 1 | 3 | 3 | Open |
| RSK-023 | ENVIRONMENT | general | Do môi trường demo công khai (hrm.anhtester.com) được nhiều người dùng tự do truy cập cùng lúc, có thể xảy ra tình trạng va chạm dữ liệu hoặc tài khoản admin_example bị người khác đổi mật khẩu, dẫn tới toàn bộ kịch bản kiểm thử bị chặn đứng. | 4 | Môi trường demo đào tạo mở cho cộng đồng nên khả năng bị can thiệp dữ liệu là rất cao | 5 | Không thể đăng nhập vào hệ thống và toàn bộ suite kiểm thử tự động thất bại 100% | 20 | Critical | Mitigate | Thực hiện kịch bản kiểm tra sức khỏe đầu chu kỳ (Healthcheck) xác thực tài khoản trước khi chạy suite chính | Liên hệ quản trị viên trang web hỗ trợ reset mật khẩu tài khoản demo hoặc chuyển sang tài khoản dự phòng | TC_LoginAdminSuccess thất bại với thông báo sai mật khẩu hoặc tài khoản bị khóa | QA Lead / DevOps | 2 | 4 | 8 | Open |
| RSK-024 | ENVIRONMENT | general | Do trình duyệt Google Chrome tự động cập nhật phiên bản mới làm mất tương thích với phiên bản ChromeDriver, có thể xảy ra lỗi SessionNotCreatedException khi khởi tạo WebDriver, dẫn tới không thể mở trình duyệt để kiểm thử. | 3 | Chrome tự động nâng cấp nền thường xuyên, có thể gây lệch version giữa browser và driver | 4 | Không thể kích hoạt bất kỳ kịch bản automation nào trên máy kiểm thử | 12 | High | Mitigate | Sử dụng cơ chế Selenium Manager tự động tải driver tương thích hoặc cố định phiên bản Chrome for Testing | Cập nhật thủ công ChromeDriver hoặc chuyển sang chạy trình duyệt Edge/Firefox đã cấu hình sẵn trong BaseTest | Ném ngoại lệ SessionNotCreatedException: This version of ChromeDriver only supports Chrome version X | Automation Tester | 1 | 3 | 3 | Open |
| RSK-025 | ENVIRONMENT | general | Do độ trễ mạng Internet không ổn định hoặc máy chủ demo đặt ở xa bị nghẽn băng thông, có thể xảy ra việc trang web tải chậm vượt quá PAGE_LOAD_TIMEOUT (25 giây), dẫn tới kịch bản kiểm thử bị đánh fail vì TimeoutException oan uổng. | 3 | Mạng internet dân dụng và server demo miễn phí thường có độ ổn định không đồng đều | 3 | Gây ra kết quả flaky thất thường, mất nhiều thời gian chạy lại để phân biệt bug thật với lỗi mạng | 9 | Medium | Mitigate | Cấu hình cơ chế RetryAnalyzer cho phép chạy lại tối đa 1 lần với những test case bị fail do lỗi mạng | Kiểm tra ping tới server và thực hiện chạy kiểm thử vào khung giờ thấp điểm mạng ổn định hơn | Xuất hiện ngoại lệ TimeoutException: Timed out after 25 seconds waiting for page to load | Automation Tester | 2 | 2 | 4 | Open |
| RSK-026 | ENVIRONMENT | general | Do dữ liệu rác từ các lần chạy trước tích tụ quá lớn mà không được dọn dẹp triệt để, có thể xảy ra tình trạng phân trang hiển thị chậm và tính năng tìm kiếm trả về danh sách quá dài, dẫn tới việc định vị bản ghi test bị sai lệch. | 3 | Các lần chạy test tạo ra hàng chục client, project, task nhưng không phải lúc nào teardown cũng thành công | 3 | Thao tác tìm kiếm chọn nhầm bản ghi cũ trùng tên gây sai kết quả xác minh | 9 | Medium | Mitigate | Xây dựng bộ test suite dọn dẹp định kỳ (Cleanup Suite) chuyên quét và xóa các bản ghi test có prefix định danh | Chạy suite TC_FlowDeleteClients và TC_DeleteProjects để dọn dẹp hàng loạt dữ liệu tồn đọng | Bảng dữ liệu Client/Project vượt quá 50 trang hiển thị hoặc tìm kiếm ra nhiều kết quả trùng lặp | Automation Tester | 1 | 2 | 2 | Open |
| RSK-027 | AI-USAGE | general | Do mô hình AI tạo sinh có thể bị hiện tượng ảo giác khi gợi ý mã kiểm thử hoặc locator, có thể xảy ra việc sinh ra các locator không tồn tại hoặc kỳ vọng sai nghiệp vụ, dẫn tới script kiểm thử bị lỗi ngay khi thực thi. | 3 | LLM không có quyền truy cập trực tiếp vào DOM runtime của trang web nên dễ suy diễn sai thẻ HTML | 3 | Tester tốn nhiều thời gian gỡ lỗi mã do AI sinh ra và giảm năng suất làm việc | 9 | Medium | Mitigate | Bắt buộc kiểm tra thủ công mọi đoạn mã và kiểm tra locator trên DevTools trước khi merge | Quay lại viết locator thủ công và kiểm chứng bằng Selenium IDE hoặc DevTools Console | Mã do AI sinh ra báo lỗi NoSuchElementException ngay từ lần chạy thử đầu tiên | Automation Tester | 1 | 2 | 2 | Open |
| RSK-028 | AI-USAGE | general | Do bất cẩn trong quá trình đưa bối cảnh và mã nguồn vào prompt cho mô hình AI, có thể xảy ra việc vô tình gửi thông tin nhạy cảm lên máy chủ công cộng, dẫn tới nguy cơ vi phạm an toàn thông tin. | 2 | Dự án hiện dùng tài khoản demo công khai nhưng có thể sơ suất khi cấu hình thêm các token bí mật | 4 | Rò rỉ thông tin đăng nhập hoặc vi phạm chính sách bảo mật dữ liệu nếu áp dụng cho dự án doanh nghiệp | 8 | Medium | Avoid | Ban hành quy định khử nhạy cảm dữ liệu: luôn dùng biến môi trường hoặc placeholder trước khi prompt | Lập tức đổi mật khẩu, thu hồi khóa API và yêu cầu xóa đoạn chat nếu phát hiện lọt thông tin nhạy cảm | Phát hiện chuỗi mật khẩu thật hoặc token dạng raw text xuất hiện trong file cấu hình prompt hoặc log | QA Lead / Tester | 1 | 3 | 3 | Open |
| RSK-029 | AI-USAGE | general | Do tính chất không tiền định của mô hình ngôn ngữ lớn khi tạo tài liệu và dữ liệu kiểm thử, có thể xảy ra tình trạng kết quả sinh ra không nhất quán giữa các lần nhắc, dẫn tới tài liệu rủi ro và ca kiểm thử thiếu tính tái lập. | 3 | Cùng một prompt nhưng nhiệt độ và phiên bản model khác nhau có thể cho đầu ra khác biệt | 3 | Khó theo dõi lịch sử thay đổi tài liệu và gây bất đồng bộ giữa các lần cập nhật bộ tài liệu test | 9 | Medium | Mitigate | Cố định cấu trúc dữ liệu bằng các script xác thực tự động và khóa ID rủi ro RSK-NNN cố định | Khôi phục phiên bản tài liệu ổn định trước đó từ lịch sử Git commit | Script kiểm tra cấu hình báo lỗi thiếu cột hoặc cấu trúc bảng bị biến đổi sau khi AI sinh lại | QA Lead / Tester | 1 | 2 | 2 | Open |
| RSK-030 | AI-USAGE | general | Do người kiểm thử quá phụ thuộc vào mã tự động và kịch bản do AI gợi ý mà bỏ qua việc hiểu sâu bản chất framework, có thể xảy ra tình trạng không thể giải thích hoặc bảo vệ giải pháp khi hội đồng phỏng vấn đặt câu hỏi sâu, dẫn tới giảm độ tin cậy đồ án. | 3 | Áp lực tiến độ có thể khiến người học copy code AI mà không nghiên cứu kỹ cơ chế hoạt động | 4 | Điểm số đồ án bị đánh giá thấp và không chứng minh được năng lực tự chủ trong phỏng vấn tuyển dụng | 12 | High | Mitigate | Thực hiện nguyên tắc tự giải thích: ghi chú giải thích tại sao chọn giải pháp cho từng đoạn code AI gợi ý | Dành thời gian rà soát, viết tài liệu kiến trúc kỹ thuật và luyện tập phản biện các câu hỏi xoay quanh framework | Tester không giải thích được lý do sử dụng ThreadLocal DriverManager hoặc cơ chế của TestListener | Automation Tester | 1 | 3 | 3 | Open |

---

## 5. Heat Map 5×5

Ma trận phân bổ 30 rủi ro theo Khả năng xảy ra (Likelihood) và Mức độ ảnh hưởng (Impact). Mỗi mã rủi ro `RSK-NNN` được đặt vào đúng ô tọa độ $(L, I)$ tương ứng:

| Likelihood (L) \ Impact (I) | 1 (Không đáng kể) | 2 (Nhỏ) | 3 (Vừa) | 4 (Lớn) | 5 (Nghiêm trọng) |
|---|---|---|---|---|---|
| **5 (Gần như chắc chắn)** | *(Trống)* | *(Trống)* | *(Trống)* | *(Trống)* | *(Trống)* |
| **4 (Khả năng cao)** | *(Trống)* | *(Trống)* | RSK-014, RSK-018, RSK-019 | RSK-005, RSK-017 | RSK-023 |
| **3 (Có thể)** | *(Trống)* | *(Trống)* | RSK-003, RSK-009, RSK-011, RSK-012, RSK-015, RSK-020, RSK-021, RSK-025, RSK-026, RSK-027, RSK-029 | RSK-002, RSK-006, RSK-013, RSK-024, RSK-030 | RSK-001, RSK-010 |
| **2 (Ít khả năng)** | *(Trống)* | RSK-004, RSK-007, RSK-008 | *(Trống)* | RSK-022, RSK-028 | RSK-016 |
| **1 (Hiếm gặp)** | *(Trống)* | *(Trống)* | *(Trống)* | *(Trống)* | *(Trống)* |

> **Thống kê phân bổ theo mức rủi ro**:
> - **Critical** ($\ge 20$): 1 rủi ro (3.3%) – [RSK-023]
> - **High** ($10 - 16$): 13 rủi ro (43.3%) – [RSK-001, RSK-002, RSK-005, RSK-006, RSK-010, RSK-013, RSK-014, RSK-016, RSK-017, RSK-018, RSK-019, RSK-024, RSK-030]
> - **Medium** ($5 - 9$): 13 rủi ro (43.3%) – [RSK-003, RSK-009, RSK-011, RSK-012, RSK-015, RSK-020, RSK-021, RSK-022, RSK-025, RSK-026, RSK-027, RSK-028, RSK-029]
> - **Low** ($\le 4$): 3 rủi ro (10.0%) – [RSK-004, RSK-007, RSK-008]
> - Tổng tỷ lệ **High + Critical**: $14 / 30 = 46.7\%$ (thỏa mãn ngưỡng quy định $\le 60\%$).

---

## 6. Ánh xạ kiểm thử theo rủi ro

Bảng dưới đây quy định phương hướng kiểm thử cụ thể cho toàn bộ 18 rủi ro thuộc hai nhóm **PRODUCT** và **AUTOMATION**:

| ID | Test level | Test type | Technique | Depth | Automate | Run label | Done criteria |
|---|---|---|---|---|:---:|---|---|
| RSK-001 | System | Security | Boundary Value Analysis & Exploratory Testing | Extensive | Y | regression | 100% kịch bản kiểm tra truy cập URL Admin bằng session Client trả về HTTP 403 Forbidden hoặc redirect về Login |
| RSK-002 | System | Security | Equivalence Partitioning & Error Guessing | Extensive | Y | smoke | Form đăng nhập hiển thị thông báo lỗi rõ ràng khi nhập sai; gửi liên tiếp 5 lần sai có cảnh báo hoặc khóa tạm thời |
| RSK-003 | System | Functional | Specification-based Testing | Balanced | Y | regression | Số lượng dự án và nhân viên hiển thị trên widget Dashboard khớp 100% với số lượng bản ghi thực tế trong cơ sở dữ liệu |
| RSK-004 | System | GUI / Usability | Exploratory Testing | Opportunity-based | N | regression | Thanh navigation hiển thị đầy đủ, không bị vỡ layout hoặc ẩn menu trên cả 3 độ phân giải màn hình kiểm thử |
| RSK-005 | System | Functional | Boundary Value Analysis & Equivalence Partitioning | Extensive | Y | regression | Hệ thống chặn tạo Client khi trùng Username/Email và hiển thị thông báo lỗi hợp lệ; không tạo bản ghi rác trong DB |
| RSK-006 | System | Functional | State Transition Testing & Error Guessing | Balanced | Y | regression | Thao tác xoá Client có dự án đang hoạt động phải hiển thị thông báo cảnh báo hoặc chặn xoá để bảo vệ tính toàn vẹn dữ liệu |
| RSK-007 | System | Functional | Equivalence Partitioning & Boundary Value Analysis | Opportunity-based | Y | regression | 100% test case kiểm thử định dạng email không hợp lệ và số điện thoại chứa chữ cái bị chặn kèm thông báo lỗi tương ứng |
| RSK-008 | System | Functional | Decision Table Testing | Opportunity-based | Y | regression | Dropdown phân công nhân sự vào dự án chỉ hiển thị các nhân viên có trạng thái hoạt động (Active); không xuất hiện Inactive |
| RSK-009 | System | Functional | Boundary Value Analysis | Balanced | Y | regression | Hệ thống chặn lưu khi chọn End Date trước Start Date của dự án và hiển thị thông báo yêu cầu khoảng thời gian hợp lệ |
| RSK-010 | System | Security | Equivalence Partitioning & Error Guessing | Extensive | Y | regression | Chức năng upload chặn toàn bộ các định dạng file không an toàn (.exe, .bat, .php, script) và chỉ chấp nhận tài liệu hợp lệ |
| RSK-011 | System | Functional | State Transition Testing | Balanced | Y | regression | Không thể cập nhật giờ làm việc hoặc thêm ghi chú khi Task đã chuyển sang trạng thái Closed; luồng chuyển trạng thái hợp lệ |
| RSK-012 | System | Functional | Boundary Value Analysis & Equivalence Partitioning | Balanced | Y | regression | Trường budget hours từ chối lưu khi nhập số âm hoặc ký tự chữ cái; tổng số giờ dự án luôn là giá trị số dương hợp lệ |
| RSK-017 | Integration | Automation Reliability | Defect-based Testing | Extensive | Y | regression | 100% test case độc lập về dữ liệu: tự tạo bản ghi với timestamp duy nhất và tự dọn dẹp; không test nào fail vì mất dữ liệu hàng row cố định |
| RSK-018 | Integration | Automation Performance | Performance Profiling | Balanced | Y | regression | Loại bỏ hoàn toàn các lệnh sleep() tĩnh cứng; toàn bộ wait chuyển sang WebDriverWait động; thời gian chạy suite giảm ít nhất 30% |
| RSK-019 | Integration | Build & Framework | Compatibility Testing | Balanced | Y | regression | Build Maven thực thi thành công không phát sinh GraphOrchestrator NPE; suite test chạy ổn định cả ở chế độ đơn luồng và đa luồng |
| RSK-020 | System | Automation Maintenance | Inspection & Refactoring | Opportunity-based | Y | regression | 100% locator XPath chỉ mục tuyệt đối được refactor sang thuộc tính bền vững (ID, name, relative XPath); tỷ lệ test flaky giảm về 0% |
| RSK-021 | Integration | Automation Portability | Portability Testing | Balanced | Y | regression | Toàn bộ đường dẫn file đính kèm chuyển sang đường dẫn tương đối qua SystemHelpers; thực thi thành công trên nhiều môi trường máy khác nhau |
| RSK-022 | Integration | Automation Integrity | Verification Testing | Opportunity-based | Y | smoke | Cấu hình pom.xml thiết lập failIfNoTests=true; build fail ngay lập tức khi có test case thất bại; không bao giờ xảy ra tình trạng xanh giả |

---

## 7. Top 10 rủi ro và hành động ưu tiên tuần này

### 7.1 Bảng xếp hạng Top 10 rủi ro nghiêm trọng nhất
| Hạng | Mã rủi ro | Phân hệ / Danh mục | Tóm tắt rủi ro | Score | Mức | Chiến lược |
|:---:|:---:|:---:|---|:---:|:---:|:---:|
| **1** | RSK-023 | ENVIRONMENT | Môi trường demo bị đổi mật khẩu hoặc sập dịch vụ | **20** | Critical | Mitigate |
| **2** | RSK-005 | client | Trùng lặp Username/Email làm hỏng dữ liệu khách hàng | **16** | High | Mitigate |
| **3** | RSK-017 | AUTOMATION | Test case phụ thuộc dữ liệu cứng trong Excel gây gãy dây chuyền | **16** | High | Mitigate |
| **4** | RSK-001 | auth | Lỗ hổng phân quyền chéo giữa tài khoản Client và Admin | **15** | High | Mitigate |
| **5** | RSK-010 | project | Upload tệp đính kèm không kiểm tra mã độc | **15** | High | Mitigate |
| **6** | RSK-002 | auth | Form đăng nhập không giới hạn số lần thử mật khẩu sai | **12** | High | Mitigate |
| **7** | RSK-006 | client | Xoá Client làm mất toàn bộ dự án liên quan | **12** | High | Mitigate |
| **8** | RSK-013 | PROJECT | Phụ thuộc duy nhất vào 1 nhân sự phát triển test script | **12** | High | Mitigate |
| **9** | RSK-014 | PROJECT | Thiếu tài liệu đặc tả yêu cầu chính thức | **12** | High | Mitigate |
| **10** | RSK-018 | AUTOMATION | Lạm dụng lệnh sleep() cứng làm suite chậm và flaky | **12** | High | Mitigate |

### 7.2 Kế hoạch hành động ưu tiên trong tuần hiện tại
1. **Ưu tiên 1 (Xử lý RSK-023 & RSK-017)**:
   - Viết kịch bản Pre-flight Healthcheck kiểm tra kết nối và đăng nhập tài khoản admin trước khi kích hoạt suite chính.
   - Refactor kịch bản `ManageClientsTest` và `ProjectsPageTest` để loại bỏ việc phụ thuộc vào số hàng `row = 2` cố định; chuyển sang sinh chuỗi ngẫu nhiên kèm timestamp `System.currentTimeMillis()`.
   - *Người phụ trách*: Võ Quỳnh Nhi | *Hạn hoàn thành*: [CẦN XÁC NHẬN: Ngày cụ thể trong tuần]
2. **Ưu tiên 2 (Xử lý RSK-018 & RSK-020)**:
   - Rà soát toàn bộ thư mục `src/test/java/projectHRM/pages/`, loại bỏ triệt để các lệnh `sleep(9)`, `sleep(5)` và thay bằng `WebUI.waitForElementVisible()` hoặc `waitForElementClickable()`.
   - Chuẩn hóa lại các XPath dài trong `ProjectsPage.java` và `ManageClientsPage.java`.
   - *Người phụ trách*: Võ Quỳnh Nhi | *Hạn hoàn thành*: [CẦN XÁC NHẬN: Ngày cụ thể trong tuần]
3. **Ưu tiên 3 (Xử lý RSK-001, RSK-005, RSK-010)**:
   - Bổ sung kịch bản kiểm thử phân quyền cho Client trong `LoginTest.java`.
   - Bổ sung kịch bản kiểm thử biên và dữ liệu trùng lặp cho Email/Username trong `ManageClientsTest.java`.
   - Thêm test case tải file không hợp lệ vào `ProjectsPageTest.java`.
   - *Người phụ trách*: Võ Quỳnh Nhi | *Hạn hoàn thành*: [CẦN XÁC NHẬN: Ngày cụ thể trong tuần]

---

## 8. Giả định, câu hỏi mở và điểm cần con người xác nhận

### 8.1 Giả định đã sử dụng
- [GIẢ ĐỊNH: Môi trường web https://hrm.anhtester.com/ duy trì hoạt động ổn định và dữ liệu demo được reset về trạng thái chuẩn định kỳ bởi ban quản trị].
- [GIẢ ĐỊNH: Toàn bộ quá trình đánh giá và nghiệm thu đồ án được thực hiện trên trình duyệt Google Chrome phiên bản mới nhất].
- [GIẢ ĐỊNH: Cấu hình Maven compiler và runtime Java 25 được hỗ trợ đầy đủ trên máy chấm đồ án mà không yêu cầu hạ cấp môi trường].

### 8.2 Điểm cần con người xác nhận
1. [CẦN XÁC NHẬN: Ngày bắt đầu và ngày hạn chót nộp bài / bảo vệ đồ án chính thức].
2. [CẦN XÁC NHẬN: Danh tính giảng viên hướng dẫn hoặc người chấm bài để điền vào phần Document Control].
3. [CẦN XÁC NHẬN: Yêu cầu của đồ án có bắt buộc thiết lập pipeline CI/CD (GitHub Actions) hay chỉ cần chạy thành công trên máy local].
4. [CẦN XÁC NHẬN: Có cần bổ sung tài khoản demo thứ hai để kiểm thử phân quyền đồng thời nhiều người dùng hay không].
5. [CẦN XÁC NHẬN: Mức độ ưu tiên của việc hạ phiên bản JDK xuống Java 17/21 LTS để kích hoạt tính năng chạy song song đa luồng ở mức Maven Surefire].

---

## 9. Tự kiểm tra chất lượng (Quality Gate Checklist)

- [x] Đầy đủ tối thiểu 28 rủi ro (hiện có 30 rủi ro được đánh số chuẩn từ `RSK-001` đến `RSK-030`).
- [x] Đủ 5 danh mục rủi ro: PRODUCT (12), PROJECT (4), AUTOMATION (6), ENVIRONMENT (4), AI-USAGE (4); mỗi danh mục $\ge 3$ rủi ro.
- [x] Phân hệ chức năng của PRODUCT bao phủ đủ 6 phân hệ cốt lõi (`auth`, `dashboard`, `client`, `employee`, `project`, `task`), mỗi phân hệ có ít nhất 2 rủi ro.
- [x] 100% mô tả rủi ro tuân thủ nghiêm ngặt cấu trúc nguyên nhân - sự kiện - hậu quả: *"Do <nguyên nhân>, có thể xảy ra <sự kiện>, dẫn tới <hậu quả>"*.
- [x] Mọi điểm Likelihood (L) và Impact (I) đều có câu lý do cụ thể bám sát bối cảnh hệ thống AUT và dự án kiểm thử; công thức $Score = L \times I$ chuẩn xác 100%.
- [x] Phân loại mức rủi ro (*Critical, High, Medium, Low*) khớp hoàn toàn với điểm số; tỷ lệ High + Critical đạt 46.7% ($\le 60\%$).
- [x] 100% rủi ro High và Critical có đầy đủ biện pháp đối phó (*Mitigation*), kế hoạch dự phòng (*Contingency*) và dấu hiệu cảnh báo sớm (*Trigger*).
- [x] Điểm rủi ro còn lại (*Residual Score*) sau xử lý luôn nhỏ hơn hoặc bằng điểm rủi ro ban đầu.
- [x] Ma trận Heat Map 5×5 phân bổ chính xác và hiển thị đầy đủ 30 mã rủi ro `RSK-001` đến `RSK-030`.
- [x] Bảng Ánh xạ kiểm thử bao phủ toàn bộ 18 rủi ro nhóm PRODUCT và AUTOMATION với đầy đủ kỹ thuật, mức chạy và tiêu chí nghiệm thu.
- [x] Tài liệu không chứa placeholder chưa điền dạng `{{...}}`, không chứa dữ liệu bịa đặt; các điểm chưa rõ được đánh dấu bằng tag `[CẦN XÁC NHẬN]`.

---

