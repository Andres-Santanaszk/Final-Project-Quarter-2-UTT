ascii_art = """"""  

for line in ascii_art.split('\n'):
    escaped_line = line.replace("\\", "\\\\").replace("\"", "\\\"")
    print(f'System.out.println("{escaped_line}");')