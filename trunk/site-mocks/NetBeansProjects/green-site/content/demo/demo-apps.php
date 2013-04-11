
<!DOCTYPE html>
<html>
    <head>
        <title>-:: GreenSource Innovation ::- DEMO</title>
        <?php 
            $selected_tab = 'DEMO';
        ?>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="/assets/css/green-style.css"/>
        <link rel="stylesheet" href="/assets/theme/redmond/jquery-ui-1.10.2.css"/>

        <script type="text/javascript" src="/assets/js/libs/jquery-1.9.1/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="/assets/js/libs/jqueryui-1.10.2/jquery-ui-1.10.2.js"></script>
    </head>
    <body >
        <div class="header">
            <?php 
                include '../../layout/header.php';
            ?>
        </div>

        <div class="body">
            <div class="home">
                <div class="blog">
                    <div>
                        <div>
                            <a href="content/demo/demo-apps.php">Demo Applications</a>
                            <p>
                                This is a demo application. It is built on Spring MVC, JPA, Hibernate and MySql database.
                            </p>
                        </div>

                    </div>
                    <div>
                        <div>
                            <a href="content/product/products.php" >All Products</a>
                            <p>
                                This is a demo application. It is built on Spring MVC, JPA, Hibernate and MySql database.
                            </p>
                        </div>

                    </div>
                    
                </div>
            </div>
        </div>

        <div class="footer">
            <?php include '../../layout/footer.php'; ?>
        </div>
    </body>
</html>
