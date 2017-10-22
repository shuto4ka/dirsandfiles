package testtask.dirsandfiles.util;

import javafx.util.Pair;
import org.springframework.context.ApplicationContext;
import testtask.dirsandfiles.domain.Path;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class PathUtil {
    private PathUtil() {
    }

    public static List<Path> getDirFirstLevelPaths(String dirName, FileSystem fileSystem) throws IOException {

        List<Path> result = new ArrayList<>();
        java.nio.file.Path path = fileSystem.getPath(dirName);
        Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 1, new SimpleFileVisitor<java.nio.file.Path>() {
            @Override
            public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
                result.add(Path.builder()
                        .name(file.getFileName().toString())
                        .size(attrs.isDirectory() ? null : attrs.size())
                        .build());
                return FileVisitResult.CONTINUE;
            }
        });
        return result;
    }

    public static int comparePaths(Path path1, Path path2) {
        if (path1.getSize() == null && path2.getSize() != null) return -1;
        if (path1.getSize() != null && path2.getSize() == null) return 1;
        int i = 0, j = 0;
        Pair<String, String> nameAndExtension1 = getLowerCaseNameAndExtension(path1);
        Pair<String, String> nameAndExtension2 = getLowerCaseNameAndExtension(path2);
        String name1 = nameAndExtension1.getKey();
        String name2 = nameAndExtension2.getKey();
        while (i < name1.length() && j < name2.length()) {
            if (Character.isDigit(name1.charAt(i)) && !Character.isDigit(name1.charAt(j))) return -1;
            if (!Character.isDigit(name1.charAt(i)) && Character.isDigit(name1.charAt(j))) return 1;
            if (Character.isDigit(name1.charAt(i)) && Character.isDigit(name2.charAt(j))) {
                Pair<Integer, Integer> numWithEndPos1 = readNumWithEndPosition(name1, i);
                Pair<Integer, Integer> numWithEndPos2 = readNumWithEndPosition(name2, j);
                if (numWithEndPos1.getKey() < numWithEndPos2.getKey()) return -1;
                if (numWithEndPos1.getKey() > numWithEndPos2.getKey()) return 1;
                i = numWithEndPos1.getValue();
                j = numWithEndPos2.getValue();
            } else {
                if (name1.charAt(i) < name2.charAt(j)) return -1;
                if (name1.charAt(i) > name2.charAt(j)) return 1;
                i++;
                j++;
            }
        }
        if (i < name1.length()) return 1;
        if (j < name2.length()) return -1;
        return compareExtensions(nameAndExtension1.getValue(), nameAndExtension2.getValue());
    }

    private static int compareExtensions(String extension1, String extension2) {
        if (extension1 == null && extension2 == null) return 0;
        if (extension1 != null && extension2 == null) return 1;
        if (extension1 == null) return -1;
        return extension1.compareTo(extension2);
    }

    private static Pair<Integer, Integer> readNumWithEndPosition(String s, int startPosition) {
        int i = startPosition;
        StringBuilder num = new StringBuilder();
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            num.append(s.charAt(i));
            i++;
        }
        return new Pair<>(Integer.parseInt(num.toString()), i);
    }

    private static Pair<String, String> getLowerCaseNameAndExtension(Path path) {
        String fullName = path.getName().toLowerCase();
        if (path.getSize() == null) return new Pair<>(fullName, null);

        StringBuilder extensionBuilder = new StringBuilder();
        int separatorIndex = fullName.length();
        for (int i = fullName.length() - 1; i >= 0; i--) {
            if (fullName.charAt(i) == '.') {
                separatorIndex = i;
                break;
            } else {
                extensionBuilder.append(fullName.charAt(i));
            }
        }
        return new Pair<>(fullName.substring(0, separatorIndex),
                path.getSize() == null ? null : extensionBuilder.reverse().toString());
    }

}