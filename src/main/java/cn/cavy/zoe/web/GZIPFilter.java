package cn.cavy.zoe.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GZIPFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(GZIPFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            
            logger.info("url:" + request.getRequestURL());
            
            String ae = request.getHeader("accept-encoding");
            if (ae != null && ae.indexOf("gzip") != -1) {
                logger.info("url:" + request.getRequestURL() + ",accept-encoding:" + ae);

                response.addHeader("Vary", "Accept-Encoding");

                GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
                chain.doFilter(req, wrappedResponse);
                wrappedResponse.finishResponse();
                return;
            }
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig filterConfig) {
        // noop
    }

    public void destroy() {
        // noop
    }

    public class GZIPResponseWrapper extends HttpServletResponseWrapper {

        protected HttpServletResponse origResponse = null;

        protected ServletOutputStream stream = null;

        protected PrintWriter writer = null;

        public GZIPResponseWrapper(HttpServletResponse response) {
            super(response);
            origResponse = response;
        }

        public ServletOutputStream createOutputStream() throws IOException {
            return (new GZIPResponseStream(origResponse));
        }

        public void finishResponse() {
            try {
                if (writer != null) {
                    writer.close();
                } else {
                    if (stream != null) {
                        stream.close();
                    }
                }
            } catch (IOException e) {
            }
        }

        public void flushBuffer() throws IOException {
            if (stream != null)
                stream.flush();
        }

        public ServletOutputStream getOutputStream() throws IOException {
            if (writer != null) {
                throw new IllegalStateException("getWriter() has already been called!");
            }

            if (stream == null)
                stream = createOutputStream();
            return (stream);
        }

        public PrintWriter getWriter() throws IOException {
            if (writer != null) {
                return (writer);
            }

            if (stream != null) {
                throw new IllegalStateException("getOutputStream() has already been called!");
            }

            stream = createOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));
            return (writer);
        }

        public void setContentLength(int length) {
        }
    }

    public class GZIPResponseStream extends ServletOutputStream {

        protected ByteArrayOutputStream baos = null;

        protected GZIPOutputStream gzipstream = null;

        protected boolean closed = false;

        protected HttpServletResponse response = null;

        protected ServletOutputStream output = null;

        public GZIPResponseStream(HttpServletResponse response) throws IOException {
            super();
            closed = false;
            this.response = response;
            this.output = response.getOutputStream();
            baos = new ByteArrayOutputStream();
            gzipstream = new GZIPOutputStream(baos);
        }

        public void close() throws IOException {
            if (closed) {
                throw new IOException("This output stream has already been closed");
            }
            gzipstream.finish();

            byte[] bytes = baos.toByteArray();

            response.addHeader("Content-Length", Integer.toString(bytes.length));
            response.addHeader("Content-Encoding", "gzip");
            output.write(bytes);
            output.flush();
            output.close();
            closed = true;
        }

        public void flush() throws IOException {
            if (closed) {
                throw new IOException("Cannot flush a closed output stream");
            }
            gzipstream.flush();
        }

        public void write(int b) throws IOException {
            if (closed) {
                throw new IOException("Cannot write to a closed output stream");
            }
            gzipstream.write((byte) b);
        }

        public void write(byte b[]) throws IOException {
            write(b, 0, b.length);
        }

        public void write(byte b[], int off, int len) throws IOException {
            if (closed) {
                throw new IOException("Cannot write to a closed output stream");
            }
            gzipstream.write(b, off, len);
        }

        public boolean closed() {
            return (this.closed);
        }

        public void reset() {
            // noop
        }
    }

}
