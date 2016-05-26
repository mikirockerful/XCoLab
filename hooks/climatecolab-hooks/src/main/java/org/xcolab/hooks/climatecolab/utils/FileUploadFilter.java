package org.xcolab.hooks.climatecolab.utils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.utils.FileUploadUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadFilter implements Filter {

	public static final int IMAGE_CROP_WIDTH_PIXELS = 300;
	public static final int IMAGE_CROP_HEIGHT_PIXELS = 300;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		InputStream is = null;
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload fileUpload = new ServletFileUpload();
				FileItemIterator items = fileUpload.getItemIterator(request);

				Boolean keepFormat = false;
				while (items.hasNext()) {
					FileItemStream item = items.next();
					if(item.isFormField() && item.getFieldName().equals("keepFormat")){
						keepFormat = true;
						continue;
					}
					if (!item.isFormField()
							&& item.getContentType().contains("image")) {
						// currently we support only images
						long imageId = CounterLocalServiceUtil
								.increment(Image.class.getName());
						//
						//
						is = item.openStream();

						byte[] imgBArr = IOUtils.toByteArray(is);
						if(!keepFormat){
							imgBArr = FileUploadUtil.resizeAndCropImage(ImageIO.read(new ByteArrayInputStream(imgBArr)),
									IMAGE_CROP_WIDTH_PIXELS, IMAGE_CROP_HEIGHT_PIXELS);
						}
						
						//Image img = ImageLocalServiceUtil..getImage(imgBArr);
						Image img = ImageLocalServiceUtil.createImage(imageId);

						FileEntry file = new FileEntry();


						//ImageLocalServiceUtil.u
						//img.setImageId(imageId);
						img.setModifiedDate(new Date());
						ImageLocalServiceUtil.addImage(img);
						ImageLocalServiceUtil.updateImage(imageId, imgBArr);

						// return JSON with image id
						response.getWriter().append(String.format("{\"success\": \"true\", \"imageId\": %d}", img.getPrimaryKey()));
						response.getWriter().close();
					}
					else {
                        response.getWriter().append(
                                "{\"error\": \"true\", \"message\":\"Invalid file format, only images are accepted\"}");
                        response.getWriter().close();
					}
				}
			}
			else {
                response.getWriter().append("{\"error\": \"true\", \"message\":\"Unknown request\"}");
                response.getWriter().close();
			}
		} catch (FileUploadException | SystemException | PortalException | IOException e) {
			throw new ServletException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// it's bad but ignore that
				}
			}
		}
	}

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doPost((HttpServletRequest) request, (HttpServletResponse) response);
        
    }

    @Override
	public void destroy() {
        // TODO Auto-generated method stub
        
    }
}