
package com.texas.anexus.cloud;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

/**
 * All the cloudinary related resources are available here.
 * 
 * @author Deependra
 * @version 1.0.0
 * @since 1.0.0, May 22, 2018
 */
public class CloudinaryResource {
	private static final Logger LOG = LoggerFactory.getLogger(CloudinaryResource.class);
	private Cloudinary cloudinary;

	public CloudinaryResource() {

//		init();
		Map config = ObjectUtils.asMap("cloud_name", "anexus", "api_key", "593999386533781", "api_secret",
				"LK1ximU6xVfNztF0lIXmheg5Mqk");
		System.out.println("From Cloudinary------------------------");
		Cloudinary cloudinary = new Cloudinary(config);
		this.cloudinary = cloudinary;
	}

	private void init() {
//		Map config = ObjectUtils.asMap("cloud_name", "anexus", "api_key",
//				"593999386533781", "api_secret", "LK1ximU6xVfNztF0lIXmheg5Mqk");
//		Cloudinary cloudinary = new Cloudinary(config);
//		this.cloudinary = cloudinary;
	}

	public String uploadFile(String file, String fileDirectory) throws IOException {
		Map uploadResult = this.cloudinary.uploader().upload(fileDirectory.concat(File.pathSeparator).concat(file),
				ObjectUtils.emptyMap());
		LOG.debug("Uploaded result {}", uploadResult);
		return null;
	}

	public String uploadFile(File file) throws IOException {
		Map uploadResult = this.cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		LOG.debug("Uploaded result {}", uploadResult);
		return null;
	}

	/**
	 * Uploads the file and returns the publicId which can be used to retrive
	 * specific file from the server..
	 * 
	 * @param file
	 * @param directory
	 * @throws IOException
	 * @author Deependra
	 * @since 1.0.0
	 */
	public String uploadFile(File file, String directory) throws IOException {

	
		Map params = ObjectUtils.asMap("public_id", directory, "overwrite", true, "resource_type", "image");
		System.out.println("--------UploadFile-----------------");
		System.out.println("---->>>>>>>>>"+file);
		Map uploadResult = this.cloudinary.uploader().upload(file, params);
		LOG.debug("Uploaded result {}", uploadResult);
		String publicId = uploadResult.get("public_id").toString().concat(".")
				.concat(uploadResult.get("format").toString());
		LOG.debug("Final file uploaded key {}", publicId);
		System.out.println("Printing ---------------"+publicId);
		return publicId;
	}

	/**
	 * Returns the file URL.
	 * 
	 * @param publicId
	 * @author Deependra Karki
	 * @since 1.0.0
	 */
	public String getFileUrl(String publicId) {
		String url = cloudinary.url().transformation(new Transformation().width(250).height(168).crop("fit"))
				.generate(publicId);
		return url;
	}

	/**
	 * Deletes the file based on publicId.
	 * 
	 * @param publicId
	 * @author Deependra
	 * @since 1.0.0
	 */
	public void deleteFile(String publicId) throws IOException {
		Map uploader = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
		LOG.debug("Deleted response {}", uploader);
	}
}
