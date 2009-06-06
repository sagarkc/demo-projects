using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Collections;

namespace Log2Chart
{
    class FileRWUtil
    {

        public static GenericArrayList<string> getAllLines(string fileName)
        {
            GenericArrayList<string> lines = new GenericArrayList<string>();
            
            StreamReader reader = new StreamReader(fileName);
            string line = "";
            while ((line = reader.ReadLine()) != null)
            {
                lines.add(line);
            }

            return lines;
        }

        public static GenericArrayList<LogLine> readAllLogLines(string fileName)
        {
            GenericArrayList<LogLine> logLines = new GenericArrayList<LogLine>();
            StreamReader reader = new StreamReader(fileName);
            string line = "";
            while ((line = reader.ReadLine()) != null)
            {
                if (line.Trim().Length == 0)
                    continue;
                if(!line.Contains("</tr>"))
                {
                    string nextLine = reader.ReadLine();
                    while (nextLine != null && (nextLine.Contains("</tr>") != true))
                    {
                        line += nextLine;
                        nextLine = reader.ReadLine();
                        if (nextLine == null)
                        {
                            break;
                        }
                    }
                }
                if(line.Trim().Length > 0)
                    logLines.add(LineParser.parseLine(line));
            }
            return logLines;
        }
    }
}
