using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

namespace Log2Chart
{
    class GenericArrayList<T> : ArrayList
    {
        public GenericArrayList()
            : base()
        {
            
        }

        public void add(T value)
        {
            base.Add(value);
        }

        public T get(int index)
        {
            return (T)base.ToArray()[index];
        }

    }

    
}
