package esm.aoc.y24.d09;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileList {

    private final Set<File> attempted = new LinkedHashSet<>();
    private final List<File> files;
    private final long nonEmptyCount;

    public FileList(final List<File> files) {
        this.files = new ArrayList<>(files);
        nonEmptyCount = files.size() - files.stream().filter(File::empty).count();
    }

    public void rearrange() {
        while (attempted.size() < nonEmptyCount) {
            rearrangeSingle();
        }
    }

    private void rearrangeSingle() {
        final var right = getNextRight();
        if (right != null) {
            final var empty = getEmptyLeft(right.size(), files.indexOf(right));
            if (empty > 0) {
                replace(empty, right);
            }
        }
    }

    private void remove(final File file) {
        int index = files.indexOf(file);
        files.remove(file);
        int newSpace = file.size();
        // Also remove any empty space to right
        if (index < files.size() && files.get(index).empty()) {
            newSpace += files.remove(index).size();
        }
        // and to the left
        if (index > 0 && files.get(index - 1).empty()) {
            newSpace += files.remove(index - 1).size();
            index --;
        }
        // Replace with empty space
        files.add(index, new File(0, newSpace, true));
    }

    private void replace(final int emptyIndex, final File toReplace) {
        final var empty = files.remove(emptyIndex);
        remove(toReplace);
        files.add(emptyIndex, toReplace);
        if (toReplace.size() < empty.size()) {
            files.add(emptyIndex + 1, new File(0, empty.size() - toReplace.size(), true));
        }
    }

    private File getNextRight() {
        for (int i = files.size() - 1; i >= 0; i--) {
            final var file = files.get(i);
            if (!file.empty() && !attempted.contains(file)) {
                attempted.add(file);
                return file;
            }
        }
        return null;
    }

    private int getEmptyLeft(final int size, final int index) {
        for (int i = 0; i < index; i++) {
            final var file = files.get(i);
            if (file.empty() && file.size() >= size) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return files.stream().map(File::toString).collect(Collectors.joining(" "));
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Integer> expand() {
        return files.stream().map(file -> file.expand(0)).flatMap(Collection::stream).toList();
    }
}
