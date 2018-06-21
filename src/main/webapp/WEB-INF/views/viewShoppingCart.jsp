<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 align="center">ショッピングカート一覧</h2>

    <p align="center">カートに商品がありません</p>

        <table border ="1"  align="center">
            <tr>
                <th colspan="2">商品名</th>
                <th>価格</th>
                <th>個数</th>
                <th></th>
            </tr>
            <tr>
				<td><a href="itemDetail.html"><img src="../img/pc.jpg" width="150"height="125" alt="商品画像"></a></td>
                <td><a href="itemDetail.html">パソコン</a></td>
                <td>&yen;50,000</td>
                <td>1個</td>
                <td>
                    <form action="viewShoppingCart.html" method="post">
                        <input type="hidden" name="item.id" value="1">
                        <input type="submit" value="削除">
                    </form>
                </td>
            </tr>
            <tr>
				<td><a href="itemDetail.html"><img src="../img/mouse.jpg" width="150"height="125" alt="商品画像"></a></td>
                <td><a href="itemDetail.html">マウス</a></td>
                <td>&yen;500</td>
                <td>2個</td>
                <td>
                    <form action="viewShoppingCart.html" method="post">
                        <input type="hidden" name="item.id" value="1">
                        <input type="submit" value="削除">
                    </form>
                </td>
            </tr>
        </table><br>

    <div  align="center"><a href="makePayment.html">決済へ</a></div>
</body>
</html>