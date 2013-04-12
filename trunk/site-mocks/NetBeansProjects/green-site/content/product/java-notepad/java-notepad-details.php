
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
                                <a href="../file-splitter/file-splitter-details.php" class="prev">Previous</a> |
                            </li>
                            <li>
                                <a href="../products.php">RETURN TO PRODUCTS</a> |
                            </li>
                            <li>
                                <a href="../bagh-bandi/bagh-bandi-details.php" class="next">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div>
                    <img src="Java-notepad.png" alt="">
                    <div>
                        <h2>Java Notepad</h2>
                        <p>
                            Java NotePad? is a very simple easy to use notepad. 
                            It is like the windows note pad. But, unlike the 
                            windows note pad, it has a few extra features. 
                            Those extra features can be available in other tools 
                            in the internet. But I just wanted to include those 
                            features with the note pad, because all those are related to text files. 
                        </p>
                        <a href="https://code.google.com/p/demo-projects/" target="_blank">Try Now</a>
                    </div>
                </div>
                <div>
                    <p>
                        The extra features are :
                    <ul>
                        <li>You can read selected number of lines from a very large file instead of 
                            reading the complete file. For example, say you have a Log file of 1GB 
                            or more. That file the normal notepad cannot open. Using this feature 
                            you can be able to read the last say 100 lines. You can export the text 
                            from the note pad to : HTML, RTF and PDF</li>
                        <li>You can convert the different files, like, XML to csv and Excel OR csv and excel to xml</li>
                        <li>One more feature is to split text files. This feature is introduced mainly 
                            to split very large XML files. But other text files can also be splitted.
                            And also the splitted parts can be combined togather to get the complete 
                            single file. All other features are like the Notepad only.</li>
                        
                    </ul>
                    </p>
                    <p>
                        <a href="http://demo-projects.googlecode.com/files/java-notepad-1.0-beta-4.zip" target="_blank">More details</a>

                    </p>
                    <p>
                        Download the latest version 
                        <a href="http://demo-projects.googlecode.com/files/java-notepad-1.0-beta-4.zip" target="_blank">
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
