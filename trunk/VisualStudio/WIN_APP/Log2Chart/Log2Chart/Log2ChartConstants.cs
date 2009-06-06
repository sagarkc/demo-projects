using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Log2Chart
{
    class Log2ChartConstants
    {
        public static string LOG_FILE_EXTENSION = "*.log";

        public static string STANDARD_LOG_ENTRY_COL1_FONT = "font1";
        public static string ADMIN_ACTION_COL1_FONT = "font2";
        public static string REACTION_TO_RULE_VIOLATION_COL1_FONT = "font3";

        public static string TYPED_TEXT_COL2_FONT = "font0";
        public static string APPLICATION_USAGE_COL2_FONT = "font1";
        public static string ADMIN_ACTION_COL2_FONT = "font2";
        public static string _COL2_FONT = "font3";
        public static string INTERNET_USAGE_COL2_FONT = "font4";

        public static string ADMIN_ACTION_LOGOFF = "*.log";
        public static string ADMIN_ACTION_LOGON = "*.log";
        public static string ADMIN_ACTION_CHANGE_SETTINGS = "Setting Change";

        public static string STARTING_TR_TOKEN = "<tr>";
        public static string ENDING_TR_TOKEN = "</tr>";
        public static string STARTING_TD_TOKEN = "<td";
        public static string ENDING_TD_TOKEN = "</td>";
        public static string STARTING_COMMENT_TOKEN = "<!---";
        public static string ENDING_COMMENT_TOKEN = "--->";

        public static char TAG_START = '<';
        public static char TAG_END = '>';
        public static char END_TAG_IDENTIFIER = '/';
        public static char QUOTE_IDENTIFIER = '\"';

        public static string DONE = "DONE";
        public static string FAILED = "FAILED";

        public static Dictionary<string, char> HTML_ASCII_CHAR_MAP
            = new Dictionary<string, char>();

        static Log2ChartConstants()
        {
            HTML_ASCII_CHAR_MAP.Add("&nbsp;", ' ');
            //HTML_ASCII_CHAR_MAP.Add("&nbsp;", ' ');
        }
    }
}
