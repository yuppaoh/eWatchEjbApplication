<%-- 
    Document   : login_register
    Created on : Feb 22, 2021, 12:54:12 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/css_img/LoginRegister.css">
    </head>
    <body>
        <section class="forms-section">
            <h1 class="section-title">E-Watch Store</h1>
            <div class="forms">
                <div class="form-wrapper is-active">
                    <button type="button" class="switcher switcher-login">
                        Login
                        <span class="underline"></span>
                    </button>
                    <form class="form form-login" action="LoginController/login()" method="POST">
                        <fieldset>
                            <!--<legend>Please, enter your email and password for login.</legend>-->
                            <div class="input-block">
                                <label>Username</label>
                                <input type="text" name="UserName" placeholder="Please enter username...." required >
                                <span class="focus-input100" data-placeholder="&#xe82a;"></span>
                            </div>
                            <div class="input-block">
                                <label>Password</label>
                                <input type="password" name="UserPassWord" placeholder="Please enter password...." required>
                            </div>
                        </fieldset>
                        <button type="submit" class="btn-login">Login</button>
                    </form>
                </div>


                <!--               register-->
                <div class="form-wrapper">
                    <button type="button" class="switcher switcher-signup">
                        Sign Up
                        <span class="underline"></span>
                    </button>
                    <form class="form form-signup">
                        <fieldset>
                            <!--                            <legend>Please enter your username and your password.</legend>-->
                            <div class="input-block">
                                <label>Username</label>
                                <input id="signup-username" type="text" name="usernameRegis"  placeholder="Please enter your username...."  required>
                            </div>
                            <div class="input-block">
                                <label>Password</label>
                                <input id="signup-password" type="password"  name="passwordRegis"   placeholder="Please enter your password...."  required>
                            </div>

                        </fieldset>
                        <button type="submit" class="btn-signup">Sign Up</button>
                    </form>
                </div>
            </div>
        </section>

        <script>
            const switchers = [...document.querySelectorAll('.switcher')]
            switchers.forEach(item => {
                item.addEventListener('click', function () {
                    switchers.forEach(item => item.parentElement.classList.remove('is-active'))
                    this.parentElement.classList.add('is-active')
                })
            })
        </script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </body>
</html>
