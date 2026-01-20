# Кількість вершин і ребер
n = 8
m = 13

# Ребра згідно варіанту
edges = [
    (1, 8), (2, 1), (2, 3), (3, 5), (3, 7), (4, 2), (4, 3),
    (5, 4), (6, 5), (6, 7), (7, 1), (8, 6), (8, 7)
]

# Функція для побудови матриці інцидентності
def create_incidence_matrix(n, edges):
    matrix = [[0] * len(edges) for _ in range(n)]
    for i, (v, u) in enumerate(edges):
        matrix[v-1][i] = -1  # Початкова вершина
        matrix[u-1][i] = 1   # Кінцева вершина
    return matrix

# Функція для побудови матриці суміжності
def create_adjacency_matrix(n, edges):
    matrix = [[0] * n for _ in range(n)]
    for v, u in edges:
        matrix[v-1][u-1] = 1  # Є ребро між вершинами
    return matrix

# Функція для виведення матриці
def print_matrix(matrix, title):
    print(f"\n{title} Matrix:")
    for row in matrix:
        print(" ".join(map(str, row)))

# Створюємо і виводимо матриці
incidence_matrix = create_incidence_matrix(n, edges)
print_matrix(incidence_matrix, "Incidence")

adjacency_matrix = create_adjacency_matrix(n, edges)
print_matrix(adjacency_matrix, "Adjacency")
