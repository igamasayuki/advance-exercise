新卒エンジニア研修

〇正規表現課題
~課題2　解答例~
com.example.ec_201804d.form.UserRegistrationForm.java

〇バッチ処理作成課題
・作成したcsvファイル: src/main/resourcesに保存.(applicatoin.ymlと同じ場所)
・作成するcsvファイルｌの場所は自分で指定.

~課題1,課題2　解答例~
com.example.ec_201804d.DoBatchItemRegister.java
com.example.ec_201804d.ECAggregator.java
com.example.ec_201804d.ItemProcessoritem.java
com.example.ec_201804d.JobStartEndListner.java

〇WebAPI操作課題
~課題1　解答例~
src/main/webapp/WEB-INF/views/paymentConfirmation.jsp

~課題2　解答例~
-カード決済WebAPI呼び出し
com.example.ec_201804d.form.CardDetailInfo.java
com.example.ec_201804d.domain.ResponseCardPaymentApi.java
com.example.ec_201804d.service.CardPaymentApiCallService.java
com.example.ec_201804d.controller.CardPaymentApiCallController.java
com.example.ec_201804d.controller.PaymentController.java

~課題3　解答例~
-キャンセル処理WebAPI呼び出し
(共通)
com.example.ec_201804d.form.OrderNumberForm.java
com.example.ec_201804d.domain.ResponseCancelAPIDomain.java
com.example.ec_201804d.service.CallCancelApiService.java
(管理者側)
com.example.ec_201804d.controller.OrderStatusController.java
(利用者側)
com.example.ec_201804d.controller.PaymentController.java

〇WebAPI側の作成課題
~課題4　解答例~
WEB-API　課題４は以下のリポジトリに解答例があります.
 https://172.16.0.14/gitbucket/igamasayuki/web-api-sample
 対象クラス
 jp.co.runy.web_api_sample.CreditCardWebApiController.java