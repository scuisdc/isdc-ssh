<%--
  Created by IntelliJ IDEA.
  User: mao
  Date: 17-9-9
  Time: 下午4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="/">
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="四川大学信息安全与网络攻防协会网站"/>
    <meta name="keywords" content="网络攻防,信息安全,四川大学"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row card" style="padding: 20px;">
        <div class="row">
            <img class="responsive-img col s12 l4" src="https://scuisdc.org/assets/logo_dark.png">
            <h4 class="col s12 l8">招新报名表</h4>
        </div>
        <form class="col s12" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <input placeholder="王大锤" id="name" type="text" required
                           class="validate">
                    <label for="name">姓名</label>
                </div>
                <div class="input-field col s12">
                    <input placeholder="2017141463XXX" id="stuId" minlength="13" maxlength="13" type="number" required
                           class="validate">
                    <label for="stuId">学号</label>
                </div>
                <div class="col s12">
                    性别：
                    <input name="gender" type="radio" id="male" value="male" checked/>
                    <label for="male">男</label>
                    <input name="gender" type="radio" id="female" value="female"/>
                    <label for="female">女</label>
                    <input name="gender" type="radio" id="transgender" value="transgender"/>
                    <label for="transgender">其他</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <input placeholder="仅用于后续通知" id="phone" type="number" class="validate" required>
                    <label for="phone">手机号</label>
                </div>
                <div class="input-field col s6">
                    <input placeholder="保证不外泄：）" id="email" type="text" class="validate" required>
                    <label for="email">邮箱</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <textarea id="description" class="materialize-textarea" placeholder="兴趣爱好，性格特点，技术基础，为什么想要加入社团，etc."
                              length="200"></textarea>
                    <label for="description">和我们介绍一下自己吧！</label>
                </div>
            </div>
            <div class="row center-align">
                <button class="btn-large waves-effect waves-light" disabled>提交</button>
            </div>
        </form>
    </div>
</div>

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/vue/2.3.4/vue.min.js"></script>
<script>

</script>
</body>
</html>
