using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Log2Chart
{
    class Map<K, V> : Dictionary<K, V>
    {

        public Map() :base()
        {

        }

        public void put(K key, V value)
        {
            if (!ContainsKey(key))
            {
                Add(key, value);
            }
        }

        public V get(K key)
        {
            V v = default(V);
            if (ContainsKey(key))
            {
                for (int i = 0; i < Keys.Count; i++)
                {
                    if(Keys.ToArray()[i].Equals(key))
                    {
                        v = (V)this.ElementAt(i).Value;
                        break;
                    }
                }
            }
            return v;
        }
    }
}
