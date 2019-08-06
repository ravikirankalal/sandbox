//package com.haha.git;
//
//import com.google.inject.Inject;
//import com.google.inject.Singleton;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import org.kohsuke.github.*;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Set;
//
//@Slf4j
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
//@Singleton
//public class GithubClient {
//
//    private final GithubConfig config;
//    /**
//     *
//     * @param gitRepo
//     * @param commitSHA eg : "e783c20e931efd9df69f0da36c45e61fdeca32c7"
//     * @param directoryPath eg : "production/DEMAND_PLANNING/v1"
//     * @param localDirectoryPath eg : "/tmp/20180211"
//     * @param fetchRecursive eg : "true" flag which indicates whether to recursively fetch all directories
//     * @throws IOException
//     */
//    public void fetchDirectoryContent(String gitRepo, String commitSHA, String directoryPath, String localDirectoryPath, boolean fetchRecursive) throws IOException {
//        validateRepoName(gitRepo);
//        GitHub gitHub = GitHub.connectToEnterpriseWithOAuth(config.getApiUrl(),config.getLogin(),config.getToken());
//        GHRepository repository = gitHub.getRepository(gitRepo);
//        validateCommit(repository, commitSHA);
//        downloadDirectoryContent(repository,commitSHA,directoryPath,localDirectoryPath,fetchRecursive,config.getAllowedFileExtensions());
//    }
//
//    public boolean isValidFileInGithubForGivenCommitId(String gitRepo, String gitFolder, String gitFilePath, String commitSHA) throws IOException {
//        String completeFilePath = String.format("%s/%s", gitFolder, gitFilePath);
//        GitHub gitHub = GitHub.connectToEnterprise(config.getApiUrl(), config.getToken());
//        GHRepository repository = gitHub.getRepository(gitRepo);
//        return config.getAllowedFileExtensions().contains(getFileExtensionInUpperCase(completeFilePath)) && repository.getFileContent(completeFilePath,commitSHA).isFile();
//    }
//
//    private void downloadDirectoryContent(GHRepository repository, String commitSHA, String remoteDirectoryPath, String localDirectoryPath, boolean fetchRecursive, Set<String> allowedFileExtensions) throws IOException {
//        List<GHContent> contentList = repository.getDirectoryContent(remoteDirectoryPath, commitSHA);
//        if (Files.exists(Paths.get(localDirectoryPath))) {
//            FileUtils.deleteDirectory(new File(localDirectoryPath));
//        }
//        Files.createDirectory(Paths.get(localDirectoryPath));
//        for (GHContent ghContent : contentList) {
//            String currentLocalPath = localDirectoryPath + File.separator + ghContent.getName();
//            if (ghContent.isDirectory() && fetchRecursive) {
//                downloadDirectoryContent(repository, commitSHA, ghContent.getPath(), currentLocalPath, fetchRecursive, allowedFileExtensions);
//            } else {
//                if (ghContent.getPath().contains(".") && allowedFileExtensions.contains(getFileExtensionInUpperCase(ghContent.getPath()))) {
//                    downloadFileContent(ghContent, currentLocalPath);
//                }
//            }
//        }
//    }
//    private String getFileExtensionInUpperCase(String filePath) {
//        return filePath.substring(filePath.lastIndexOf(".") + 1).toUpperCase();
//    }
//
//    private void downloadFileContent(GHContent ghContent, String localFilePath) throws IOException {
//        Files.deleteIfExists(Paths.get(localFilePath));
//        Files.createFile(Paths.get(localFilePath));
//        InputStream inputStream = ghContent.read();
//        OutputStream outputStream = new FileOutputStream(localFilePath);
//        IOUtils.copy(inputStream, outputStream);
//        IOUtils.closeQuietly(inputStream);
//        IOUtils.closeQuietly(outputStream);
//    }
//
//    private void validateRepoName(String repoName) throws IOException {
//        if (!repoName.matches(".*/.*")) {
//            throw new IOException("Repo Name should be of the format <Organization>/<Repo Name>");
//        }
//    }
//
//    private void validateCommit(GHRepository repository, String commitSHA) throws IOException {
//        GHCommit commit = repository.getCommit(commitSHA);
//        if (commit.getFiles().isEmpty()) {
//            throw new IOException("Empty changes in given commit : " + commitSHA);
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        GithubClient githubClient = new GithubClient(new GithubConfig("https://github.fkinternal.com/api/v3","appanasatya","83b69d6c8260a372f0a20585a01b028bdc3cc127","R,py"));
//        githubClient.fetchDirectoryContent("Flipkart/ipp-models","77131bd03cf8094ed855f4a8bf92e3ec8b2903db","production/DEMAND_PLANNING/","/tmp/githubtest",true);
//        int x =0;
////        ZipUtils.zipDirectory("/tmp/githubtest","/tmp/out.zip",true);
////        ZipUtils.unzipFileIntoDirectory("/tmp/out.zip","/tmp/zipout",true);
//    }
//}