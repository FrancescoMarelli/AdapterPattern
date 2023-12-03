import java.util.List;

public class ListAdapter implements IArray {
    private List<Integer> lista;

    public ListAdapter(List<Integer> lista) {
        this.lista = lista;
    }

    @Override
    public void anadir(int valor) {
        lista.add(valor);
    }

    @Override
    public void eliminar(int posicion) {
        lista.remove(posicion);
    }

    @Override
    public void vaciar() {
        if (!lista.isEmpty()) {
            lista.clear();
        }
    }

    @Override
    public boolean esVacio() {
        return lista.isEmpty();
    }

    @Override
    public int tamano() {
        return lista.size();
    }

    @Override
    public int primero() {
        if (!lista.isEmpty()) {
            return lista.get(0);
        } else {
            throw new IllegalStateException("La lista está vacía.");
        }
    }

    @Override
    public int ultimo() {
        if (!lista.isEmpty()) {
            return lista.get(lista.size() - 1);
        } else {
            throw new IllegalStateException("La lista está vacía.");
        }
    }

    @Override
    public int devolverPosicion(int posicion) {
        if (posicion >= 0 && posicion < lista.size()) {
            return lista.get(posicion);
        } else {
            throw new IndexOutOfBoundsException("Posición fuera de rango.");
        }
    }
}
