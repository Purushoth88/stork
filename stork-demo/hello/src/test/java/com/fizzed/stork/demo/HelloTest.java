/*
 * Copyright 2016 Fizzed, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fizzed.stork.demo;

import static com.fizzed.stork.demo.TestHelper.which;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.zeroturnaround.exec.ProcessExecutor;

public class HelloTest {
    
    Path exe = which(Paths.get("target/stork/bin"), "stork-demo-hello");
    
    @Test
    public void execute() throws Exception {
        String output
            = new ProcessExecutor()
                .command(exe.toString())
                .readOutput(true)
                .exitValue(0)
                .execute()
                .outputUTF8();
        
        assertThat(output, containsString("Hello World"));
    }
    
    @Test
    public void arguments() throws Exception {
        String arg0 = "128401";
        String arg1 = "ahs3h1";
        
        String output
            = new ProcessExecutor()
                .command(exe.toString(), arg0, arg1)
                .readOutput(true)
                .exitValue(0)
                .execute()
                .outputUTF8();
        
        assertThat(output, containsString("Hello World"));
        assertThat(output, containsString(arg0));
        assertThat(output, containsString(arg1));
    }
    
}
