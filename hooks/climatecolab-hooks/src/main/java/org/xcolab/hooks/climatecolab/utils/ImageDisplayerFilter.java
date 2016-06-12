package org.xcolab.hooks.climatecolab.utils;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.exceptions.FileEntryNotFoundException;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageDisplayerFilter implements Filter {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String imageId = null;

        // image/contest?img_id=1272201
        if (request.getParameter("img_id") != null) {
            imageId = request.getParameter("img_id");
        }

        // image/user_male_portrait?screenName=carlosbpf&companyId=10112&portraitId=2390159
        if (request.getParameter("portraitId") != null) {
            if(request.getParameter("userId") != null){
                try {
                    Member member = MembersClient.getMember(Long.parseLong(request.getParameter("userId")));
                    imageId = member.getPortraitFileEntryId() + "";
                } catch (MemberNotFoundException e) {
                    imageId = request.getParameter("portraitId");
                }
            }else {
                imageId = request.getParameter("portraitId");
            }
        }

        String path = request.getSession().getServletContext().getRealPath("/");
        if (imageId != null && !imageId.isEmpty()) {
            try {
                FileEntry fileEntry = FilesClient.getFileEntry(new Long(imageId));
                String filePath = FilesClient.getFilePathFromFinalDestination(fileEntry, path);
                if (filePath != null) {
                    sendImageToResponse(request, response, filePath);
                    return;
                }


            } catch (FileEntryNotFoundException ignored) {
            }
        }

        if(request.getRequestURI().contains("user_male_portrait")){
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            if(themeDisplay != null) {
                String pathToFailOverImage = path + "../" + themeDisplay.getPathImage() + "user_default.png";
                sendImageToResponse(request, response, pathToFailOverImage);
            }else{
                String pathToFailOverImage = path + "images/user_default.png";
                sendImageToResponse(request, response, pathToFailOverImage);
            }
            return;

        }
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException ignored) {

        }
    }

    private void sendImageToResponse(HttpServletRequest request, HttpServletResponse response, String filePath) {

        try {
            ServletContext cntx = request.getSession().getServletContext();
            String mime = cntx.getMimeType(filePath);
            if (mime == null) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            response.setContentType(mime);
            File file = new File(filePath);
            response.setContentLength((int) file.length());

            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            int count = 0;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            out.close();
            in.close();
            return;
        } catch (IOException ignored) {

        }
        try{
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }catch (IOException ignored){

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doGet((HttpServletRequest) request, (HttpServletResponse) response);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
