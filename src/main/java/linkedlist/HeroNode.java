package linkedlist;

public class HeroNode {
    public Hero hero;
    public HeroNode next;
    public HeroNode pre;
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public HeroNode(Hero hero) {
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }

    @Override
    public String toString() {
        return hero.toString();
    }
}