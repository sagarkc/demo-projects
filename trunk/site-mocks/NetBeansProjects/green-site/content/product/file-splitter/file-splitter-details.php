
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
                                <a href="../oracle-gui/oracle-gui-details.php" class="prev">Previous</a> |
                            </li>
                            <li>
                                <a href="../products.php">RETURN TO PRODUCTS</a> |
                            </li>
                            <li>
                                <a href="../java-notepad/java-notepad-details.php" class="next">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div>
                    <img src="file-splitter.png" alt="File Splitter">
                    <div>
                        <h2>GSplit :: File Splitter</h2>
                        <p>
                            Small GUI tool for splitting large file into parts.
                        </p>
                        <a href="https://sourceforge.net/projects/gsplit/" target="_blank">Try Now</a>
                    </div>
                </div>
                <div>
                   <p>
                    <h2>File Split</h2>
                    <p>File split fundamentally is to read few blocks of a source file and creating 
                        a new file with that block. The subsequent blocks are stored in next parts, 
                        i.e. create a new file for each of the blocks. The output blocks can be 
                        re-joined to create the original file. <br/>
                    <pre>
Input file: 	| 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | …
Output parts: 	| 1 | 0 | 1 | 0 |    | 1 | 0 | 1 | 0 |  | 1 | 0 | 1 | 0 |  | 1 | 0 | 1 | 0 | … 
                    Part-0			part-1		part-2			part-3
                    
                    </pre>
                    </p>
                   
                    
                    </p>
                    <p>
                         <h2>File Join</h2>
                        After splitting the source file, we can re-arrange the generated parts into the original file. All the parts (0 – n-1) to create the original file.
                    </p>
                    <p>In this case, if a new block is added, which was not present in the original file, and joined all the block again, the generated file will not be the same file as the original one. This leads to a problem of corrupted file after joining all the blocks.</p>
                    <p>
                        GSplit offers a validation check of all the parts generated at the time of joining them to create the original file.
                    </p>
                    <p>
                        While splitting the source file each part is appended a 40-byte validation information. The same validation information is checked at the time of re-assembling all the parts. Also, it creates a metadata information of the parts created at the time of splitting. The metadata file is the input of the Join process to create the original file (provided, all the parts must be present in the same path as the metadata file).
                    </p>
                    <p>
                        In each part, the header is organized as:
                    <pre>
Block – 1: 	4-byte 	> the total number of parts created.
Block – 2: 	4-byte 	> the current part number.
Block – 3:	32-byte	> an unique hash-code is generated from the current part’s file name, which is irreversible.

                    </pre>
                    The above 40-byte is appended in the beginning of each part. Then the data from the source file follows.
                    </p>
                    <p>
                        <a href="https://sourceforge.net/projects/gsplit/" target="_blank">More details</a>

                    </p>
                    <p>
                        Download the latest version 
                        <a href="https://sourceforge.net/projects/gsplit/" target="_blank">
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
