using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using com.gs.text4net.core.parser.csv;
using com.gs.text4net.core.cfg;

namespace com.gs.text4net.core
{
    class TestCore
    {
        public void test()
        {
            CsvParser parser = new CsvConfiguration().configure().
                buildParserFactory().buildParser();

        }
    }
}
