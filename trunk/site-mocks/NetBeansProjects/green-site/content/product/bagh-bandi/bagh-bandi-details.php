
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
                                <a href="../java-notepad/java-notepad-details.php" class="prev">Previous</a> |
                            </li>
                            <li>
                                <a href="../products.php">RETURN TO PRODUCTS</a> |
                            </li>
                            <li>
                                <a href="#" class="next">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div>
                    <img src="bagh-bandi.PNG" alt="">
                    <div>
                        <h2>Bagh Bandi :: - Surround the Tiger</h2>
                        <p>
                            Bagh bandi is a two-player abstract strategy board game from Lower Bengal, India. 
                            It is a hunt game. It uses an Alquerque board, and therefore, Bagh bandi 
                            is specifically a tiger hunt game (or tiger game). There are two tigers 
                            attempting to elude and capture as many goats while the goats are attempting 
                            to surround and trap the tigers.
                        </p>
                        <a href="https://sourceforge.net/projects/bagh-bandi/" target="_blank">Try Now</a>
                    </div>
                </div>
                <div>
                    <p>
                        <h2>Goal</h2>
                        The goats win if they surround and immobilize the two tigers.<br/>
                        The tigers win if they capture enough goats so that they cannot immobilize the tigers.
                    </p>
                    <p>
                        <h2>Equipment</h2>
                        The board is an Alquerque board. There are 2 tigers represented as 2 black pieces. There are 20 goats represented as 20 white pieces.
                    </p>
                    <p>
                        <h2>Game Play and Rules</h2>
                        <p>1. Players decide which animal to play.</p>
                        <p>2. In the beginning, the 2 tigers are placed on the middle column. Specifically, one tiger is placed on the second point from the top of the middle column, and the other tiger is placed on the fourth point. The goats are placed on the second left most column and second right most column. The goats are specifically placed on the first point and third point of each column from the top. Therefore, there are four points on the board that all the 20 goats are placed upon initially, and each of the four points has 5 goats each.</p>
                        <p>3. Players alternate their turns throughout the game. Only one piece may used to move or capture per turn. The goats start first.</p>
                        <p>4. The goat player may take the top goat of a pile, and place it onto any vacamt adjacent point following the pattern on the board. Or the goat player can take any single goat on the board and move it one space onto any vacant adjacent point following the pattern on the board. A goat may not move onto a pile, or onto a single goat piece to form a pile.</p>
                        <p>Similarly, a tiger may be moved one space onto a vacant adjacent point following the pattern on the board.</p>
                        <p>5. A tiger can capture by the short leap as in <a href="/wiki/Draughts" title="Draughts">draughts</a> or Alquerque. The tiger leaps over the adjacent goat, and lands on a vacant point immediately beyond. The leap must be in a straight line and follow the pattern on the board. A tiger can also leap over a pile of goats, but only captures the top most goat of the pile, and lands onto a vacant point on the other side. Again, the jump must be in a straight line and follow the pattern on the board. Only one capture is allowed per turn, however, another source states that multiple captures are allowed. If multiple captures are allowed, the tiger, however, cannot jump back and forth on the same pile to capture all of its pieces in one turn. Captures are not compulsory.</p>
                        <p>Goats cannot capture.</p>
                    </p>
                    <p>
                        <a href="http://en.wikipedia.org/wiki/Bagh_bandi" target="_blank">More details</a>
                    </p>
                    
                    <p>
                        Download the latest version 
                        <a href="https://sourceforge.net/projects/bagh-bandi/" target="_blank">
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
