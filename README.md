# 研修終了後 追加課題 解答例

研修後追加課題の解答例プロジェクトです。  
以下に課題ごとにクラス名が書かれていますが、そのクラス内に解答例の実装が含まれています。答え合わせやどうしてもわからない場合のみ確認するようにしましょう。
- - -

## 正規表現課題
### ~課題2　解答例~
#### パスワード堅牢化処理
* com.example.ec_201804d.form.UserRegistrationForm.java

- - -

## バッチ処理作成課題
### 前提
* 作成したcsvファイル: src/main/resourcesに保存.(applicatoin.ymlと同じ場所)  
* 作成するcsvファイルの保存場所はプログラムで指定しています

### ~課題1,課題2　解答例~
#### CSV→DB, DB→CSV
* com.example.ec_201804d.DoBatch.java
* com.example.ec_201804d.ECAggregator.java
* com.example.ec_201804d.JobStartEndListner.java

- - -

## WebAPI操作課題
### ~課題1　解答例~
#### クレジットカード情報入力フォーム
* src/main/webapp/WEB-INF/views/paymentConfirmation.jsp

### ~課題2　解答例~
#### カード決済WebAPI呼び出し
* com.example.ec_201804d.form.CardDetailInfo.java
* com.example.ec_201804d.domain.ResponseCardPaymentApi.java
* com.example.ec_201804d.service.CardPaymentApiCallService.java
* com.example.ec_201804d.controller.CardPaymentApiCallController.java
* com.example.ec_201804d.controller.PaymentController.java

### ~課題3　解答例~
#### キャンセル処理WebAPI呼び出し
##### (共通)
* com.example.ec_201804d.form.OrderNumberForm.java
* com.example.ec_201804d.domain.ResponseCancelAPIDomain.java
* com.example.ec_201804d.service.CallCancelApiService.java

##### (管理者側)
* com.example.ec_201804d.controller.OrderStatusController.java

##### (利用者側)
* com.example.ec_201804d.controller.PaymentController.java

- - -

## WebAPI側の作成課題
### ~課題4　解答例~
#### 呼び出される側のWEB-API実装は以下のリポジトリに解答例があります
 https://172.16.0.14/gitbucket/igamasayuki/web-api-sample  
* 対象クラス：jp.co.runy.web_api_sample.CreditCardWebApiController.java
