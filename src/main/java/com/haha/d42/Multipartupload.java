package com.haha.d42;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Multipartupload {
    public static void main(String[] args) {
        Multipartupload multipartupload = new Multipartupload();
        multipartupload.publish();
    }

    private void publish() {
        String blobName = "testblob";
        String bucket = "testBucket";
        String blobFileLocalPath = "/home";
        String key = "57DVUF2XZUIZ8WIY27N7";
        String secretKey = "mxsJuhRGdIHaZd3fZUrZ89K52pxqG5vnbeCxKY0+";
        String endpoint = "10.47.2.33";
        try {
            AWSCredentials credentials = new BasicAWSCredentials(key, secretKey);
            ClientConfiguration clientConfig = new ClientConfiguration();
            clientConfig.setMaxErrorRetry(3);
            clientConfig.setProtocol(Protocol.HTTP);
            AmazonS3Client amazonS3Client = new AmazonS3Client(credentials, clientConfig);
            amazonS3Client.setEndpoint(endpoint);
            amazonS3Client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
            if(!(amazonS3Client.doesBucketExist(bucket))) {
                amazonS3Client.createBucket(bucket);
            }
            File file = new File(blobFileLocalPath);

//            List<PartETag> partETags = new ArrayList<PartETag>();
//
//            long contentLength = file.length();
//            //todo: Can be made configurable.
//            long partSize = 10*1024*1024; // Set part size to 10 MB.
//
//            InitiateMultipartUploadRequest initRequest = new
//                    InitiateMultipartUploadRequest(bucket, blobName);
//            InitiateMultipartUploadResult initResponse =
//                    amazonS3Client.initiateMultipartUpload(initRequest);
//
//            try {
//                // Step 2: Upload parts.
//                long filePosition = 0;
//                for (int i = 1; filePosition < contentLength; i++) {
//                    // Last part can be less than 5 MB. Adjust part size.
//                    partSize = Math.min(partSize, (contentLength - filePosition));
//
//                    // Create request to upload a part.
//                    UploadPartRequest uploadRequest = new UploadPartRequest()
//                            .withBucketName(bucket).withKey(blobName)
//                            .withPartNumber(i)
//                            .withFileOffset(filePosition)
//                            .withFile(file)
//                            .withPartSize(partSize);
//
//                    // Upload part and add response to our list.
//                    partETags.add(amazonS3Client.uploadPart(uploadRequest).getPartETag());
//                    filePosition += partSize;
//                }
//
//                // Step 3: Complete.
//                CompleteMultipartUploadRequest compRequest = new
//                        CompleteMultipartUploadRequest(
//                        bucket,
//                        blobName,
//                        initResponse.getUploadId(),
//                        partETags);
//
//                amazonS3Client.completeMultipartUpload(compRequest);
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, blobName, file);
                amazonS3Client.putObject(putObjectRequest);
            } catch (Exception e) {
//                amazonS3Client.abortMultipartUpload(new AbortMultipartUploadRequest(
//                        bucket, blobName, initResponse.getUploadId()));
            }
//        } catch (AmazonServiceException ase) {
//            throw new IllegalStateException(ase);

//        } catch (AmazonClientException ace) {
//            throw new IllegalStateException("D42 model blob PUT failed - blobname: " + blobName + " - " + ace.getMessage(), ace);
        }

//    }
}
