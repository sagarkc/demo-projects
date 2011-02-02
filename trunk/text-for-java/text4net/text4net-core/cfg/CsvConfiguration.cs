using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using com.gs.text4net.common.types;
using com.gs.text4net.core.parser;

namespace com.gs.text4net.core.cfg
{
    class CsvConfiguration
    {
        private String configurationFile;

        private Set<String> csvFileNames;
        private Set<String> mappingResources;
        private Set<String> annotatedClasses;

        public CsvConfiguration()
        {

        }

        public CsvConfiguration configure()
        {
            return this;
        }

        public CsvConfiguration configure(String fileName)
        {
            return this;
        }

        public CsvParserFactory buildParserFactory()
        {
            return null;
        }
    }
}
