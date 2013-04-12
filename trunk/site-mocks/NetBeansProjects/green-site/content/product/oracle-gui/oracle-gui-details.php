
<!DOCTYPE html>
<html>
    <head>
        <title>-:: GreenSource Innovation ::- Product</title>
        <?php
        $selected_tab = 'PRODUCTS';
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
            include '../../../layout/header.php';
            ?>
        </div>

        <div class="body">
            <div class="gallery-details">
                <div>
                    <h2>Our Products</h2>
                    <div class="paging">
                        <ul>
                            <li>
                                <a href="#" class="prev">Previous</a> |
                            </li>
                            <li>
                                <a href="../products.php">RETURN TO PRODUCTS</a> |
                            </li>
                            <li>
                                <a href="../file-splitter/file-splitter-details.php" class="next">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div>
                    <img src="oracle-gui.PNG" alt="">
                    <div>
                        <h2>Oracle GUI</h2>
                        <p>
                            Small GUI tool for Oracle database. Using this tool 
                            user can explore multiple database and multiple 
                            schemas at a same time. User can update data in the 
                            table quickly. 
                        </p>
                        <a href="https://code.google.com/p/oracle-gui/" target="_blank">Try Now</a>
                    </div>
                </div>
                <div>
                    <p>
                        Some functionalities are mentioned bellow:
                    <ul>
                        <li>Connect to an Oracle database.</li>
                        <li>View the Table details.</li>
                        <li>View the Data in a table.</li>
                        <li>Edit data in a table.</li>
                        <li>Generate Dependency Graph of a particular table with its related tables (PK and FK).</li>
                        <li>SQL editor and executor.</li>
                        <li>Export table data in TXT, HTML, XML, CSV, INSERT SCRIPTS.</li>
                        <li>Modify table Properties (ALTER, RENAME, DROP, TRUNCATE, COPY, COMMENT, etc..)</li>
                    </ul>
                    </p>
                    <p>
                        <a href="https://code.google.com/p/oracle-gui/" target="_blank">More details</a>

                    </p>
                    <p>
                        Download the latest version 
                        <a href="http://oracle-gui.googlecode.com/files/1.0-BETA-1.2.zip" target="_blank">
                            <img src="/assets/images/download_ico_64.png" alt="HERE">
                        </a>
                    </p>
                </div>
            </div>
        </div>

        <div class="footer">
            <?php include '../../../layout/footer.php'; ?>
        </div>
    </body>
</html>
