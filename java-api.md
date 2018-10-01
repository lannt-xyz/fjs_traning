# Checklist dùng cho lập trình phát triển API

Tài liệu này dùng để check lại source code khi hoàn thành coding.

Được tổng hợp từ nhiều nguồn, chủ yếu là các chia sẻ từ group `Fujinet - ソースコードのレビュー指摘`.

Đã tổng hợp các chia sẻ tại group [Fujinet - ソースコードのレビュー指摘](https://kcw.kddi.ne.jp/g/o1q2sjmthgj32h) đến ngày `2017/03/03`

## Đặt tên class, method, variable, constant

### Dễ hiểu

- Đặt tên biến, hàm dễ đọc, dễ hiểu.
- Đặt tên hàm, tên biến KHÔNG sai lỗi chính tả.

### Format tên class, method, biến, constant

- Tên class đã đặt theo format UpperCamelCase.

  ```
  AuthorRecommendations
  ```

- Tên biến, tên method đã đặt theo format lowerCamelCase.

  ```
  Tên biến: memberId
  Tên method: getUser()
  ```

- Tên hằng số thì viết in hoa toàn bộ, các từ cách nhau bằng gạch dưới.

  ```
  MEMBER_ID_LENGTH
  ```

- Tên Class ở những package khác ngoài `domain.model` mà có cùng tên với 1 class ở `domain.model`, thì thêm subfix để phân biệt (không đặt cùng tên với `domain.model`).

  ```java
  package jp.honto.domain.model;
  pulic class Recommendation {}

  NG:
  package jp.honto.presentation.dto;
  public class Recommendation {}

  OK:
  package jp.honto.presentation.dto;
  public class RecommendationDto {}
  ```

### Thống nhất

- Sử dụng tên giống nhau cho các biến, hàm có cùng mục đích sử dụng, cùng ý nghĩa.  

### Đặt tên biến/tên hàm cho kiểu dữ liệu boolean

**Không đặt tên method là checkXyz, mà sẽ đặt như dưới đây**

- can & Động từ thể nguyên mẫu

  ```java
  canCheckout()
  ```

- has & Động từ thể quá khứ

  ```java
  hasOrdered()
  ```

- has & Danh từ

  ```java
  hasOrder()
  ```

- is & Tính từ

  ```java
  isValid()
  ```

- động từ hiện tại của danh từ số ít & Danh từ

  ```java
  existsCart()
  ```

### Tên method trong DAO

- KHÔNG sử dụng dạng số nhiều của danh từ (thêm `s`, `es`), hoặc `List`
    - Cause: Thông thường thì việc select từ database sẽ get nhiều record, trong trường hợp get 1 record thì tên hàm nên thể hiện việc get 1 record.
    - Resolve: Sử dụng các tên method có cấu trúc tương tự như dưới đây, trường hợp khác thì thảo luận với các member trong nhóm
        - selectAll
        - selectById
        - selectOneByXyz

### Tên constant

- Tên constant phải mang ý nghĩa của mục đích sử dụng
    - Cause: Giúp source code dễ đọc dễ hiểu
    - Resolve: Trong ví dụ dưới đây nếu như tên hằng số là `COMMA` thì khi đọc sẽ không hiểu nó dùng để làm gì, nhưng nếu đặt là `ITEM_ID_SEPARATOR` thì có thể hiểu ngay đây là hằng số dùng để phân cách giữa các ItemID

  ```java
  NG:
  private final String COMMA = "","";

  OK:
  private final String ITEM_ID_DELIMITER = "","";
  ```

- Không dùng các constant chưa đầy đủ ý nghĩa tại BaseValidator

  ```java
  NG:
  private static final String ERROR_CODE_REQUIRED = ""required"";
  private static final String ERROR_CODE_INVALID = ""invalid"";
  private static final String ERROR_CODE_LENGTH = ""length"";
  private static final String ERROR_CODE_CHARACTER = ""character"";

  OK:
  private static final String ERROR_CODE_PREFIX_REQUIRED = ""required"";
  private static final String ERROR_CODE_PREFIX_INVALID = ""invalid"";
  private static final String ERROR_CODE_SUFFIX_LENGTH = ""length"";
  private static final String ERROR_CODE_SUFFIX_CHARACTER = ""character"";
  ```

### Other

- Tên biến phải là danh từ, cụm danh từ.
- Tên biến phải đúng mục đích và ý nghĩa sử dụng. **Không dùng lại của source cũ(hontoRest), không dùng lại cái mà đã định nghĩa tại file database nếu sai ý nghĩa**.
- Tên hàm phải bắt đầu bằng động từ.
- Từ loại tính từ phải đứng trước danh từ.

  ```java
  NG:
  selectSubscriptionLatest

  OK:
  selectLatestSubscription
  ```

- Đối với array hoặc list thì tên biến thể hiện dạng số nhiều của danh từ (thêm `s` hoặc `es`), không dùng `xyzList`
- Từ 商品 khi đặt tên biến trong tiếng Anh phải là đặt là `item` không được đăt là `product`
- Không khai báo trùng tên biến với các biến ở scope cao hơn.

## Comment

### Quy tắc chung

- Đã comment các class, method liên quan đến chức năng lập trình bao gồm method private.
    - Cause: Việc comment javadoc cho method private thì cũng không cần thiết nhưng mà để cho những người cùng phát triển hiểu được đại khái method làm việc gì thì việc comment javadoc trở nên cần thiết.
- Comment dòng thì không kết thúc bằng dấu chấm (1 byte hoặc 2 bytes).
- Dùng ký tự alphabet 1 byte cho việc comment **tuyệt đối không dùng alphabet 2 bytes**
- Khi comment javadoc thì cần thống nhất thứ tự giữa parameter của method và comment.
    - Note: Trình tự parameter của javadoc với method là khác nhau sẽ gây khó khăn khi sử dụng method

  ```java
  NG:
  /**
   * コンストラクタ.
   *
   * @param memberId 会員ID
   * @param actionId アクションID
   * @param trackingId トラッキングログID
   */
  public DefaultContent(String actionId, String trackingId, String memberId) {
  }

  OK:
  /**
   * コンストラクタ.
   *
   * @param actionId アクションID
   * @param trackingId トラッキングログID
   * @param memberId 会員ID
   */
  public DefaultContent(String actionId, String trackingId, String memberId) {
  }
  ```

- Comment của Constructor thì thống nhất là コンストラクタ

### Comment method

- Kết thúc dòng đầu tiên của comment javadoc là period `.`, KHÔNG sử dụng ký tự period 2 bytes `。`
- Kết thúc các dòng như là @param, @return, @exception, thì không cần gắn period `.`
- Khi mô tả các dòng @param, @return, @exception thì mỗi element phải nằm trên cùng 1 dòng.

  ```java
  NG:
  * @param fileKey
  *            ファイルキー

  OK:
  * @param fileKey ファイルキー
  ```

- Khi mô tả @return của các phương thức có kiểu trả về là boolean, phải mô tả kết quả trả về của cả 2 trường hợp đúng và sai.

  ```java
  NG:
  * @return キャンペーンの存在有無

  OK:
  * @return true:キャンペーンが存在している場合, false:キャンペーンが存在していない場合
  ```

- Đối với source code (ngoài source test), comment method thì KHÔNG được sử dụng các tag HTML (br, ul, li, …)
    - Note: Việc thể hiện bằng cấu trúc HTML thì không cần thiết.

### Comment trong file sql

- Comment theo cách thức `[tên column] + 1 space 1 byte + -- comment`
    - Note: Trước đây thì đang add rất nhiều space vô nghĩa, mục đích là để cho comment thành 1 cột, tuy nhiên việc này là không cần thiết vì có thể gây khó đọc trên MR

  ```java
  NG:
  SELECT
      PRD_ID,                  -- 商品ID
      PRD_NM,                  -- 商品名
      ISS_FREQ_ETC_MEMO,       -- 刊行頻度等メモ

  OK:
  SELECT
      PRD_ID, -- 商品ID
      PRD_NM, -- 商品名
      ISS_FREQ_ETC_MEMO, -- 刊行頻度等メモ
  ```

- Trường hợp mà câu comment quá dài, bị rớt dòng khi hiển thị trên merge request thì nên comment ở một dòng khác.

  ```java
  NG:
  AND PST.CONT_PUB_CLS IN ('48', '49', '50') -- フロント_商品対応端末.コンテンツ掲載区分:セット限定商品, 定期購読限定商品, 販売中

  OK:
  -- フロント_商品対応端末.コンテンツ掲載区分:セット限定商品, 定期購読限定商品, 販売中
  AND PST.CONT_PUB_CLS IN ('48', '49', '50')
  ```

### Một số từ ngữ tiếng Nhật dùng trong comment

1. ~~エナム~~ → enum
1. ~~未登録~~ → 登録無
1. ~~未更新~~ → 更新無
1. ~~未削除~~ → 削除無
1. ~~でない~~ → ではない
1. ~~未入力~~ → 未設定
1. ~~不当~~ → 不正
1. ~~超過場合~~  → 超過している場合
1. ~~入力しない場合~~  → 設定しない場合
1. ~~～を条件に~~ → ～をキーに
1. ~~未存在~~ → 存在せず
1. ~~復号化~~ → 復号
1. ~~～並び替えられる~~ →　～並び替えられている

## Xử lý trong source code

### Xử lý phải có ý nghĩa

- Không khai báo những biến số không sử dụng.
    - Note: Đối với eclipse thì những biến số không được sử dụng sẽ được warning, tuy nhiên sau khi hoàn thành thì phải confirm và xóa những biến số không sử dụng.
- Không viết code vô nghĩa hoặc không được sử dụng.
    - Note: Trong quá trình thực thi sẽ có phát sinh những đoạn code viết tạm dùng cho debug, sau khi hoàn thành thì phải confirm và xóa những xử lý này.

### Phân chia xử lý

- Không dồn quá nhiều xử lý vào một method (mỗi method thực hiện duy nhất 1 công việc).
    - Cause: Việc dồn quá nhiều xử lý vào một method sẽ làm cho source code thiếu tính linh động, nên sẽ khó bảo trì.
- Trường hợp if ... else nhiều hơn 2 lần thì nên dùng switch ... case

### Thống nhất trong xử lý

- Các xử lý tương tự nhau thì phải thực hiện coding giống nhau.
    - Cause: Dễ review, chỉnh sửa

### Common hóa

- Common hóa các xử lý giống nhau, được sử dụng nhiều thành function để dùng chung.
    - Cause: Các xử lý giống nhau tồn tại ở nhiều nơi trong source code, khi phát sinh chỉnh sửa đối với những đoạn source này sẽ phải đối ứng cả ở những nơi khác gây mất thời gian hoặc không thống nhất trong source code.

### Sử dụng hằng số

- Không được sử dụng hardcode mà phải khai báo thành hằng số đối với những giá trị lặp đi lặp lại trong 1 class
    - Cause: Khi chỉnh sửa chỉ cần thay đổi giá trị của hằng số

### Xử lý NULL

- Phải xử lý cho trường hợp đối tượng là NULL
    - Cause: Khi truy cập vào properties hoặc method của đối tượng là NULL thì sẽ phát sinh `NullPointerException`, đây là `unchecked` exception cho nên rất khó phát hiện.
    - Resolve: Khi mà không thể đảm bảo được object không phải là NULL thì nên add thêm xử lý check như ví dụ dưới đây

  ```java
  if (!ObjectUtils.isEmpty(object)) { // nếu project không dùng Spring thì check null bằng 「object != null」
      object.xyz();
  }
  ```
