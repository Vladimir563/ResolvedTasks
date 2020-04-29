Дополнительно: 
Добавить аннотацию Max, Min, NotNull
которые можно использовать для аргументов методов (полей классов)

Set<Class> classes = new HashSet<>();
classes.add(Cat.class);
classes.add(Mouse.class);
classes.add(Owner.class);
classes.add(AnimalsConfig.class);

DIContainer container = инизиализация DIContainer с передачей classes
// либо 
container.getObj("Cat").catchMouse(container.getObj("Mouse"));
// либо 
container.getObj(Cat.class).catchMouse(container.getObj(Mouse.class));


