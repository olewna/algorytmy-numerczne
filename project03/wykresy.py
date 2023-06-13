import numpy as np
import matplotlib.pyplot as plt
plt.style.use('default')

# Wczytanie danych z pliku txt
# data = np.loadtxt('f.txt', delimiter=',', skiprows=1)
data = np.loadtxt('g.txt', delimiter=',', skiprows=1)
# data = np.loadtxt('h.txt', delimiter=',', skiprows=1)

# Podzielenie danych na kolumny
wynik_wzorcowy = data[:, 0]
wynik_trapezy = data[:, 1]
wynik_simpson = data[:, 2]
wynik_csi = data[:, 3]

# Obliczenie różnicy i logarytmu naturalnego dla wyników trapezów (A1)

blad_trapezy = (wynik_wzorcowy - wynik_trapezy)
# blad_csi = (wynik_wzorcowy - wynik_csi)


# Obliczenie logarytmu naturalnego dla wyników Simpsona (A2)

blad_simpson = (wynik_wzorcowy - wynik_simpson)
# blad_simpson = (wynik_wzorcowy - wynik_simpson)

roznica_trapez_simpson = np.subtract(blad_trapezy, blad_simpson)
# roznica_simpson_csi = np.subtract(blad_simpson, blad_csi)
# print(roznica_simpson_csi)


# Tworzenie wykresu porównawczego
x = [i for i in range(1, 101)]  # Tworzenie osi x

# plt.plot(x, roznica_simpson_csi, label='Różnica błędów bezwzględnych (A2 - A3)')
plt.plot(x, roznica_trapez_simpson, label='A1 - A2')
plt.axhline(y=0, color='g', linestyle=':')

# plt.plot(x, wynik_wzorcowy, label='Wynik wzorcowy')
plt.xlabel('Numer rzędu')
# plt.ylabel('Logarytm różnicy wyników')
plt.title('Porównanie metody A2 i A1 dla G(x)')
plt.legend()

# Wyświetlenie wykresu
plt.show()
