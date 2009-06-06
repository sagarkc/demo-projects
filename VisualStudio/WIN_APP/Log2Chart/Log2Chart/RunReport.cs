using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using Log2Chart;


namespace Log2Chart
{
    class RunReport
    {
        
        private string logFolderPath;
        private GenericArrayList<string> allLogFileNames;
        private GenericArrayList<LogLine> allLogLines;
        private string reportFolderName;


        public RunReport(string path)
        {
            this.logFolderPath = path;
            reportFolderName = path + "/Reports ";
            allLogFileNames = new GenericArrayList<string>();
            allLogLines = new GenericArrayList<LogLine>();
        }

        public GenericArrayList<LogLine> process()
        {

            DirectoryInfo initialDirectory = new DirectoryInfo(logFolderPath);

            // get all the *.log file names
            getAllLogFiles(initialDirectory, Log2ChartConstants.LOG_FILE_EXTENSION, ref allLogFileNames);
            readAllLines();
            int i = allLogLines.Count;
            return allLogLines;

        }

        /*
         *      This method is to get all the *.log file name list in a folder and also in its sub-lofders
         *      recursively.
         */
        private void getAllLogFiles(DirectoryInfo directoryInfo, string searchPattern, ref GenericArrayList<string> logFiles)
        {
            foreach (FileInfo fi in directoryInfo.GetFiles(searchPattern))
            {
                logFiles.Add(fi.FullName);
            }

            foreach (DirectoryInfo di in directoryInfo.GetDirectories())
            {
                getAllLogFiles(di, searchPattern, ref logFiles);
            }
        }

        private void readAllLines()
        {
            foreach (string fn in allLogFileNames)
            {
                allLogLines.AddRange(FileRWUtil.readAllLogLines(fn));
            }
        }

        public string generateReport()
        {
            string dt = DateTime.Now.ToString("yyMMddhhmmss");
            reportFolderName += dt;
            DirectoryInfo di = new DirectoryInfo(reportFolderName);
            if (!di.Exists)
                di.Create();

            return Log2ChartConstants.DONE;
        }

        public void createCsv()
        {
            int count = 0;
            // Internet usage
            for (int i = 0; i < allLogLines.Count; i++)
            {

                LogLine ll = allLogLines.get(i);
                if (Log2ChartConstants.INTERNET_USAGE_COL2_FONT.Equals(ll.SECOND_COLUMN_FONT_NAME))
                {
                    count++;
                }
            }
        }
    }

    class LineParser
    {

        public string replaceHtmlCode(string str)
        {
            // fghdfgh &nbsp;fghdfgh &lt;fghdfgfghdfgh &nbsp;fghdfgh &lt;fghdfgfghdfgh &nbsp;fghdfgh &lt;fghdfg
            return str;
        }
        
        public static LogLine parseLine(string line)
        {
            LogLine ll = new LogLine();
            int i,j,k; 
            char[] lineChars = line.ToCharArray();
            // replace <tr> and </tr>
            line = line.Replace(Log2ChartConstants.STARTING_TR_TOKEN, "")
                .Replace(Log2ChartConstants.ENDING_TR_TOKEN, "");
            // extract the comment if any
            if (line.Contains(Log2ChartConstants.STARTING_COMMENT_TOKEN))
            {
                int cStart, cEnd;
                cStart = line.IndexOf(Log2ChartConstants.STARTING_COMMENT_TOKEN);
                cEnd = line.IndexOf(Log2ChartConstants.ENDING_COMMENT_TOKEN);
                if (cStart != -1 && cEnd != -1)
                {
                    ll.COMMENT = line.Substring(cStart + 5, cEnd - cStart - 5);
                    // delete the comment
                    line = line.Remove(cStart, cEnd - cStart+4);
                }
                if (ll.COMMENT != null)
                {
                    string[] spl = ll.COMMENT.Split(new char[] { ' '});
                    if (spl.Length == 5)
                    {
                        ll.LOG_TIME = spl[0] +" "+ spl[1] +" "+ spl[2];
                        ll.USER = spl[3];
                        ll.SYS_NAME = spl[4];
                    }
                }
            }
            string td1, td2;
            if (line != "")
            {
                td1 = line.Substring(
                        line.IndexOf(Log2ChartConstants.STARTING_TD_TOKEN),
                        line.IndexOf(Log2ChartConstants.ENDING_TD_TOKEN) + 5
                    );
                line = line.Substring(
                        line.IndexOf(Log2ChartConstants.ENDING_TD_TOKEN) + 5
                    );
                if (td1 != "")
                {
                    int qS, qE, gtE, ltS;
                    qS = td1.IndexOf(Log2ChartConstants.QUOTE_IDENTIFIER);
                    qE = td1.LastIndexOf(Log2ChartConstants.QUOTE_IDENTIFIER);
                    if (qS != -1 && qE != -1)
                    {
                        string font = td1.Substring(qS + 1, qE - qS - 1);
                        ll.FIRST_COLUMN_FONT_NAME = font;
                    }
                    
                    gtE = td1.IndexOf(Log2ChartConstants.TAG_END);
                    ltS = td1.LastIndexOf(Log2ChartConstants.TAG_START);
                    if (gtE != -1 && ltS != -1)
                    {
                        string fVal = td1.Substring(gtE + 1, ltS - gtE - 1);
                        ll.FIRST_COLUMN_VALUE = fVal;
                    }
                }
            }
            if (line != "")
            {
                td2 = line.Substring(
                        line.IndexOf(Log2ChartConstants.STARTING_TD_TOKEN),
                        line.IndexOf(Log2ChartConstants.ENDING_TD_TOKEN) + 5
                    );
                int qS, qE, gtE, ltS;
                qS = td2.IndexOf(Log2ChartConstants.QUOTE_IDENTIFIER);
                qE = td2.LastIndexOf(Log2ChartConstants.QUOTE_IDENTIFIER);
                if (qS != -1 && qE != -1)
                {
                    string font = td2.Substring(qS + 1, qE - qS - 1);
                    ll.SECOND_COLUMN_FONT_NAME = font;
                }

                gtE = td2.IndexOf(Log2ChartConstants.TAG_END);
                ltS = td2.LastIndexOf(Log2ChartConstants.TAG_START);
                if (gtE != -1 && ltS != -1)
                {
                    string fVal = td2.Substring(gtE + 1, ltS - gtE - 1);
                    ll.SECOND_COLUMN_VALUE = fVal;
                }
            }

            return ll;
        }
    }

    class LogLine
    {
        private string firstColumnFontName;
        private string firstColumnValue;
        private string secondColumnFontName;
        private string secondColumnValue;
        private string comment;
        private string userName;
        private string systemName;
        private string logTime;

        // getters setters
        public string FIRST_COLUMN_FONT_NAME
        {
            get
            {
                return firstColumnFontName;
            }
            set
            {
                firstColumnFontName = value;
            }
        }
			
        public string FIRST_COLUMN_VALUE
        {
            get
            {
                return firstColumnValue;
            }
            set
            {
                firstColumnValue =value;
            }
        }

        public string SECOND_COLUMN_FONT_NAME
        {
            get
            {
                return secondColumnFontName;
            }
            set
            {
                secondColumnFontName = value;
            }
        }

        public string SECOND_COLUMN_VALUE
        {
            get
            {
                return secondColumnValue;
            }
            set
            {
                secondColumnValue = value;
            }
        }

        public string COMMENT
        {
            get
            {
                return comment;
            }
            set
            {
                comment = value;
            }
        }

        public string USER
        {
            get
            {
                return userName;
            }
            set
            {
                userName = value;
            }
        }

        public string SYS_NAME
        {
            get
            {
                return systemName;
            }
            set
            {
                systemName = value;
            }
        }
        public string LOG_TIME
        {
            get
            {
                return logTime;
            }
            set
            {
                logTime = value;
            }
        }

        public string ToString()
        {
            return "col1 : [ " + FIRST_COLUMN_FONT_NAME + ", " + FIRST_COLUMN_VALUE + " ]\n"
                + "col2 : [ " + FIRST_COLUMN_FONT_NAME + ", " + FIRST_COLUMN_VALUE + " ]\n"
                + "comment : " + COMMENT;
        }

    }
}
