//
//  ========================================================================
//  Copyright (c) 1995-2012 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.continuation;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jetty.continuation.test.ContinuationBase;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.IO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ContinuationTest extends ContinuationBase
{
    protected Server _server = new Server();
    protected ServletHandler _servletHandler;
    protected ServerConnector _connector;
    FilterHolder _filter;

    @Before
    public void setUp() throws Exception
    {
        _connector = new ServerConnector(_server);
        _server.setConnectors(new Connector[]{ _connector });
        ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.NO_SECURITY|ServletContextHandler.NO_SESSIONS);
        _server.setHandler(servletContext);
        _servletHandler=servletContext.getServletHandler();
        ServletHolder holder=new ServletHolder(_servlet);
        holder.setAsyncSupported(true);
        _servletHandler.addServletWithMapping(holder,"/");

        _server.start();
        _port=_connector.getLocalPort();
    }

    @After
    public void tearDown() throws Exception
    {
        _server.stop();
    }
    
    @Test
    public void testContinuation() throws Exception
    {
        doNormal("HttpChannelState");
    }

    @Test
    public void testSleep() throws Exception
    {
        doSleep();
    }

    @Test
    public void testSuspend() throws Exception
    {
        doSuspend();
    }

    @Test
    public void testSuspendWaitResume() throws Exception
    {
        doSuspendWaitResume();
    }

    @Test
    public void testSuspendResume() throws Exception
    {
        doSuspendResume();
    }

    @Test
    public void testSuspendWaitComplete() throws Exception
    {
        doSuspendWaitComplete();
    }

    @Test
    public void testSuspendComplete() throws Exception
    {
        doSuspendComplete();
    }

    @Test
    public void testSuspendWaitResumeSuspendWaitResume() throws Exception
    {
        doSuspendWaitResumeSuspendWaitResume();
    }

    @Test
    public void testSuspendWaitResumeSuspendComplete() throws Exception
    {
        doSuspendWaitResumeSuspendComplete();
    }

    @Test
    public void testSuspendWaitResumeSuspend() throws Exception
    {
        doSuspendWaitResumeSuspend();
    }

    @Test
    public void testSuspendTimeoutSuspendResume() throws Exception
    {
        doSuspendTimeoutSuspendResume();
    }

    @Test
    public void testSuspendTimeoutSuspendComplete() throws Exception
    {
        doSuspendTimeoutSuspendComplete();
    }

    @Test
    public void testSuspendTimeoutSuspend() throws Exception
    {
        doSuspendTimeoutSuspend();
    }

    @Test
    public void testSuspendThrowResume() throws Exception
    {
        doSuspendThrowResume();
    }

    @Test
    public void testSuspendResumeThrow() throws Exception
    {
        doSuspendResumeThrow();
    }

    @Test
    public void testSuspendThrowComplete() throws Exception
    {
        doSuspendThrowComplete();
    }

    @Test
    public void testSuspendCompleteThrow() throws Exception
    {
        doSuspendCompleteThrow();
    }
    
    @Override
    protected String toString(InputStream in) throws IOException
    {
        return IO.toString(in);
    }
    
}
