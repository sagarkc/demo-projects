using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;

namespace MasterCommander
{
    class CommandProcess
    {
        private static CommandProcess instance;

        private int ExitCode;
        private ProcessStartInfo ProcessInfo;
        private Process Process;

        private CommandProcess() 
        {
            ProcessInfo = new ProcessStartInfo("cmd.exe");
            ProcessInfo.CreateNoWindow = true;
            ProcessInfo.UseShellExecute = false;
        }

        public static CommandProcess getInstance()
        {
            if (null == instance)
            {
                instance = new CommandProcess();
            }
            return instance;
        }

        public Process createNewProcess(bool createNewWindow)
        {
            Process = Process.Start(ProcessInfo);
            return Process;
        }
    }
}
