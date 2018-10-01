### Xử lý NULL

- Phải xử lý cho trường hợp đối tượng là NULL
    - Cause: Khi truy cập vào properties hoặc method của đối tượng là NULL thì sẽ phát sinh `NullPointerException`, đây là `unchecked` exception cho nên rất khó phát hiện.
    - Resolve: Khi mà không thể đảm bảo được object không phải là NULL thì nên add thêm xử lý check như ví dụ dưới đây

  ```java
  if (!ObjectUtils.isEmpty(object)) { // nếu project không dùng Spring thì check null bằng 「object != null」
      object.xyz();
  }
  ```

- Check length của chuỗi ký tự trước khi xử lý cắt chuỗi
    - Cause: Khi cắt chuỗi với index vượt quá chiều dài của chuỗi thì sẽ phát sinh `StringIndexOutOfBounds` exception, đây là `unchecked` exception cho nên rất khó phát hiện.
    - Resolve: Nên dùng các utilities như là `StringUtils`, trường hợp project hiện tại chưa bao gồm thư viện `commons-lang3` thì xem xét add thêm hoặc check tính hợp lệ của `index` và `length` trước khi cắt chuỗi.

  ```java
  // Sample dưới đây đang dùng 「org.apache.commons.lang3.StringUtils」
  StringUtils.substring(null, *)   = null
  StringUtils.substring("""", *)     = """"
  StringUtils.substring(""abc"", 0)  = ""abc""
  StringUtils.substring(""abc"", 2)  = ""c""
  StringUtils.substring(""abc"", 4)  = """"
  StringUtils.substring(""abc"", -2) = ""bc""
  StringUtils.substring(""abc"", -4) = ""abc""

  // Sample dưới đây dùng method của java-core để cắt chuỗi
  int startIndex = 5;
  int length = 2;
  String sample = ""abcdefghi"";
  if ((startIndex + length) < sample.length()) {
    sample.subString(startIndex, length);
  }
  ```

### Xử lý Exception

- Không được sử dụng `ex.printStackTrace` trong `catch` thay vào đó là một xử lý có ý nghĩa, như là ghi log hoặc throw

  ```java
  NG:
  try {
      xxxx
  } catch (XyzException | AbcException ex) {
      ex.printStackTrace();
  }

  OK:
  try {
      xxxx
  } catch (XyzException | AbcException ex) {
      throw new MemberApiException(NOT_FOUND);
  }
  ```

### Câu lệnh SQL

- Không JOIN các table ngoài phạm vi của API đang thực hiện (trường hợp cần thiết thì phải confirm với member chuyên trách)
    - Cause: Hiện tại database của các hệ như là 商品・会員・受注 thì đang dùng chung, sau này sẽ chia ra các database riêng lẽ đối với từng hệ cho nên việc JOIN các table ngoài phạm vi thì có khả năng sau này phải chỉnh sửa lại API.
- Không dồn quá nhiều xử lý logic vào câu lệnh SQL.
    - Cause: Việc có quá nhiều xử lý logic tại câu lệnh SQL sẽ gây khó đọc/khó hiểu đối với câu lệnh SQL dẫn đến khó bảo trì/sửa chữa.
    - Resolve: Phân chia câu lệnh SQL lớn thành những lệnh nhỏ, các xử lý logic đặt tại model.
    - Note: Trường hợp tại resource hiện hành dùng 1 câu SQL phức tạp thì phải đọc hiểu toàn bộ xử lý bên trong SQL và phân rã cho phù hợp.
- SELECT、FROM、WHERE、ORDER BY、GROUP BY thì cho là indentl level giống nhau.

  ```java
  SELECT
      PRD_ID, -- 商品ID
      ITM_CLS, -- アイテム区分
      PRD_FMT_CLS, -- 商品フォーマット区分
      TAX_IN_PRC, -- 税込価格
      TAX_EX_PRC -- 税抜価格
  FROM
      PF_BK_PRD_BAS -- フロント_商品基礎(書籍)
  WHERE
      PRD_ID IN /*itemIds*/('00000000','11111111') -- 商品ID
      AND ITM_CLS <> '2' -- アイテム区分 : 電子書籍以外
      AND LGC_DEL_FLG = '0' -- 論理削除フラグ : 有効
  ```

### Enum

- Tên method trong enum dùng `of` đối với việc nhận vào `lowercaseName` và chuyển thành enum.

  ```java
  public static MemberRank of(int lowercaseName) {
    return Stream.of(MemberRank.values())
      .filter(c -> c.lowercaseName == lowercaseName)
      .findFirst()
      .orElse(null);
  }
  ```

- Tên method trong enum dùng `fromXyz` đối với việc nhận vào giá trị khác `lowercaseName` và chuyển thành enum.

  ```java
  private final String rank;

  public static MemberRank fromRank(String rank) {
    return Stream.of(MemberRank.values())
      .filter(c -> c.rank == rank)
      .findFirst()
      .orElse(null);
  }
  ```

- Trường hợp tại nơi sử dụng enum cho phép nhận vào giá trị null thì tại các class enum trả về giá trị bao gồm null (không trả về optional)

  ```java
  public static MemberRank fromValue(int value) {
    return Stream.of(MemberRank.values())
      .filter(c -> c.value == value)
      .findFirst()
      .orElse(null);
  }
  ```

### Request lock

- Không gắn `TABLE_SERVICE` vào LockId, confirm LockId với member chuyên trách trước khi thực thi
    - Note: Việc gắn `TABLE_SERVICE` vào LockId hoàn toàn không có ý nghĩa

### Output log

- Thực hiện output log có level(error, warning) tương ứng với source hiện hành (Honto REST)
    - Note: Việc thực hiện output log là để theo dõi trạng thái phát sinh error của API, nên việc output log với level error, warning là cần thiết
        - Nên confirm với member chuyên trách về việc output log
        - Không output với level `debug`
        - Các level khác thì confirm với member chuyên trách
- Thực hiện output TrackingLog tương ứng với xử lý tại source hiện hành (source hiện hành có output TrackingLog thì mới thực hiện)
    - Note: TrackingLog là log output dùng cho `sản phẩm khuyến khích` và nhiều mục đích khác, vì vậy log này là cần thiết

### Stream API

- Khi check tồn tại của 1 item trong list thì sẽ sử dụng `anyMatch` chứ không phải `.findFirst().isPresent()`

  ```java
  public boolean contains(CampaignId campaignId) {
    return enteredSpecialPointCampaigns.stream()
        .anyMatch(e -> campaignId.equals(e.getCampaignId()));
  }
  ```

## Trình bày source code

### Trình bày đẹp

- Dễ đọc
- Mỗi dòng code là 1 xử lý **tối đa gọi hàm (dùng period) 2 lần**
- Dòng code không quá dài, tối đa 120 ký tự (setting eclipse).
- Chỉnh indent cho source code sau khi code xong (setting formatter).
- Tất cả indent là ký tự space không sử dụng ký tự tab, ứng với mỗi indent là 2 space.
- Chỉ áp dụng tool format của IDE cho đoạn code mà mình sửa, không áp dụng format đối với source có sẵn.
- Nên có sự quan tâm khi class vượt quá 100 dòng.
"
