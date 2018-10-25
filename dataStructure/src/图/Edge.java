package 图;

public class Edge <T extends Comparable <T>> implements Comparable<Edge> {
    private int from;
    private int to;
    private T weight;

    public Edge(int from , int to , T weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(){
        this.from = -1;
        this.to = -1;
        this.weight = null;
    }

    public int getOther(int x){
        if (x != from && x != to) {
            throw new IllegalArgumentException("参数错误");
        }
        return x == from ? to : from;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public T getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ from:").append(this.from).append(" to:").append(this.to).append(" weight:").append(this.weight).append(" ]");
        return sb.toString();
    }

    @Override
    public int hashCode(){
        int temp = 31;
        int hashCode = 0;
        hashCode = this.from * temp + hashCode;
        hashCode = this.to * temp + hashCode;
        hashCode = this.weight.hashCode() * temp + hashCode;
        return hashCode;
    }

    @Override
    public boolean equals(Object o){
        if (null == o) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        return this.weight.compareTo((T) edge.weight) != 0 && this.to == ((Edge) o).to && this.from == ((Edge) o).from;
    }

    @Override
    public int compareTo(Edge o) {
        return this.getWeight().compareTo((T) o.getWeight());
    }
}
