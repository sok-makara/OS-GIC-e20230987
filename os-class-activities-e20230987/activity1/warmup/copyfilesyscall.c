#include <fcntl.h>
#include <unistd.h>
int main() {
    char buffer[1024];
    ssize_t bytesRead;
    int src = open("result.txt", O_RDONLY);
    if (src < 0) { write(2, "Error: cannot open result.txt\n", 30); return 1; }
    int dst = open("copyresult.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (dst < 0) { write(2, "Error: cannot create copyresult.txt\n", 36); close(src); return 1; }
    while ((bytesRead = read(src, buffer, sizeof(buffer))) > 0) write(dst, buffer, bytesRead);
    close(src);
    close(dst);
    write(1, "File copied successfully!\n", 26);
    return 0;
}
