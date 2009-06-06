using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Xml.Serialization;
using System.IO;

namespace XmlSplitter
{
    [Serializable]
    [XmlRoot("ROOT")]
    public class ProductList
    {
        [XmlElement("Products")]
        public List<ProductDetail> productCollection = new List<ProductDetail>();
    }
    
    [Serializable]
    public class ProductDetail
    {
        public string ProductCode;
        public string ProductName;
        public string ProductLongDescription;
        public string Barcode;
        public string Brand;
        public string Price;
        public string Category1;
        public string OnSpecial;
        public string SpecialPrice;
        public string ProductURL;
        public string PreOrderItem;
        public string ReleaseDate;
        public string ProductKeywords;
        public string ImageURL;
        public string ShippingEstimate;
        public string StockLevel;
        public string Title2;
    }
    
    
    class XmlProcessorUtil
    {


        public static List<ProductDetail> processXml(string xmlFileName)
        {
            long numberOfRecordsProcessed = 0;

            XmlTextReader reader = new XmlTextReader(xmlFileName);
            StringBuilder str = new StringBuilder();
            ProductList pList = new ProductList();
            List<ProductDetail> pl = new List<ProductDetail>();
            List<string> un = new List<string>();
            // loop through all the nodes
            while (reader.Read())
            {
                if (XmlNodeType.Element == reader.NodeType)
                {
                    if (reader.Name != "ROOT" && reader.Name != "Products")
                    {
                        ProductDetail pd = new ProductDetail();
                        do{
                            if (reader.Name == "ProductCode")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ProductCode = reader.Value;
                                }
                            }
                            if (reader.Name == "ProductName")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ProductName = reader.Value;
                                }
                            }
                            if (reader.Name == "ProductLongDescription")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ProductLongDescription = reader.Value;
                                }
                            }
                            if (reader.Name == "Barcode")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.Barcode = reader.Value;
                                }
                            }
                            if (reader.Name == "Brand")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.Brand = reader.Value;
                                }
                            }
                            if (reader.Name == "Price")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.Price = reader.Value;
                                }
                            }
                            if (reader.Name == "Category1")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.Category1 = reader.Value;
                                }
                            }
                            if (reader.Name == "OnSpecial")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.OnSpecial = reader.Value;
                                }
                            }
                            if (reader.Name == "SpecialPrice")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.SpecialPrice = reader.Value;
                                }
                            }
                            if (reader.Name == "ProductURL")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ProductURL = reader.Value;
                                }
                            }
                            if (reader.Name == "PreOrderItem")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.PreOrderItem = reader.Value;
                                }
                            }
                            if (reader.Name == "ReleaseDate")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ReleaseDate = reader.Value;
                                }
                            }
                            if (reader.Name == "ProductKeywords")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ProductKeywords = reader.Value;
                                }
                            }
                            if (reader.Name == "ImageURL")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ImageURL = reader.Value;
                                }
                            }
                            if (reader.Name == "ShippingEstimate")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.ShippingEstimate = reader.Value;
                                }
                            }
                            if (reader.Name == "StockLevel")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.StockLevel = reader.Value;
                                }
                            }
                            if (reader.Name == "Title2")
                            {
                                reader.Read();
                                if (XmlNodeType.Text == reader.NodeType)
                                {
                                    pd.Title2 = reader.Value;
                                }
                            }
                            //else
                            //{
                            //    string sa = reader.Name;
                            //    reader.Read();
                            //    if (XmlNodeType.Text == reader.NodeType)
                            //    {
                            //        un.Add(sa + " : " + reader.Value);
                            //    }
                            //}
                            
                            bool b = reader.Read();
                            if (!b)
                                break;
                        }while(reader.Name != "Products");
                        pl.Add(pd);
                    }
                }
                
            }
            numberOfRecordsProcessed = pl.Count;
            return pl;
        }

        public static void split(long recordCount, List<ProductDetail> pl, string path)
        {
            path = path + "/part_001.xml";
            ProductList list = new ProductList();
            for (int i = 0; i < recordCount; i++)
            {
                ProductDetail d = pl.ToArray()[i];
                list.productCollection.Add(d);
            }
            
            TextWriter tw = new StreamWriter(path);
            XmlSerializer s = new XmlSerializer(typeof(ProductList));
            
            s.Serialize(tw, list);
            tw.Close();

        }
    }
}
