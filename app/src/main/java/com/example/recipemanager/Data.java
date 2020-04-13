package com.example.recipemanager;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Data {

    public ArrayList<Recipe> Recipes(){
        ArrayList<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("McSinghs Scotch pie", "Heat a large frying pan and toast the cumin seeds for a few minutes," +
                " then set aside. Heat the oil in the same pan and fry the onion, garlic, chilli, pepper and a good pinch of salt for around" +
                " eight minutes, until there is no moisture left. Remove from the heat, stir in the toasted cumin seeds, ground mace (or nutmeg)" +
                " and ground coriander. Leave to cool. In a large bowl mix together the minced lamb, white pepper, fresh coriander, and the cooled" +
                " spiced onion mixture until combined. Set aside, covered, in the fridge. Preheat the oven to 200C/400F/Gas 6 and generously grease" +
                " a 20cm/8in diameter loose-bottomed or springform round cake tin with lard. To make the pastry, sift the flour and salt in a large" +
                " bowl and make a well in the centre. Put the milk, lard and 90ml/3fl oz of water in a saucepan and heat gently. When the lard has" +
                " melted, increase the heat and bring to the boil. Pour the boiling liquid into the flour, and use a wooden spoon to combine until" +
                " cool enough to handle. Bring together into a ball. Dust a work surface with flour and, working quickly, knead the dough briefly" +
                " – it will be soft and moist. Set aside a third of the pastry and roll the rest out on a well-floured surface. Line the pie dish " +
                "with the pastry, pressing it right up the sides until it pokes just over the top of the tin. Add the filling into the pastry-lined" +
                " tin bit by bit. As you reach the top, form a slight peak. Roll out the reserved pastry and top the pie with it. Pinch the edges to" +
                " seal and trim the excess. Poke a hole in the top of the pie and insert a small tube made from aluminium foil to allow steam to escape." +
                " Brush the top of the pie with a little beaten egg yolk, and bake in the preheated oven for 30 minutes (put a tray on the shelf below" +
                " to catch any drips). Then reduce the temperature to 160C/325F/Gas 3 and cook for a further 1¼ hours until golden-brown. Leave to cool" +
                " completely before refrigerating for two hours, or overnight. Run a knife around the edge of the pie, remove from the tin and serve with" +
                " chutneys, salads or pickles.", R.drawable.mcsinghsscotchpie));
        recipes.add(new Recipe("Peach & Blueberry Grunt", "Heat oven to 190C/170C fan/gas 5. Butter a wide shallow ovenproof dish. Blend" +
                " the cornflour with the orange zest and juice, and put in a large pan with the sugar. Halve, stone and slice the peaches and add to the" +
                " pan. Bring slowly to the boil, stirring gently until the sauce is shiny and thickened, about 3-4 mins. Remove from the heat, stir in the" +
                " blueberries and tip into the prepared dish. Tip the flour into a mixing bowl and add the 50g butter. Rub the butter into the flour until" +
                " it resembles fine breadcrumbs, then stir in half the sugar. Mix the remaining sugar with the cinnamon and set aside. Add the milk to the" +
                " dry ingredients and mix to a soft dough. Turn out onto a lightly floured surface and knead briefly. Roll out to an oblong roughly 16 x" +
                " 24cm. Brush with melted butter and sprinkle evenly with the spicy sugar. Roll up from one long side and cut into 12 slices. Arrange around" +
                " the top of the dish, leaving the centre uncovered. Bake for 20-25 mins, until the topping is crisp and golden. Serve warm."
                , R.drawable.peachandblueberrygrunt));
        recipes.add(new Recipe("Smoked Haddock Kedgeree", "Melt 50g butter in a large saucepan (about 20cm across), add 1 finely chopped medium" +
                " onion and cook gently over a medium heat for 5 minutes, until softened but not browned. Stir in 3 split cardamom pods, ¼ tsp turmeric, 1 small" +
                " cinnamon stick and 2 bay leaves, then cook for 1 minute. Tip in 450g basmati rice and stir until it is all well coated in the spicy butter." +
                " Pour in 1 litre chicken or fish stock, add ½ teaspoon salt and bring to the boil, stir once to release any rice from the bottom of the pan." +
                " Cover with a close-fitting lid, reduce the heat to low and leave to cook very gently for 12 minutes. Meanwhile, bring some water to the boil" +
                " in a large shallow pan. Add 750g un-dyed smoked haddock fillet and simmer for 4 minutes, until the fish is just cooked. Lift it out onto a" +
                " plate and leave until cool enough to handle. Hard-boil 3 eggs for 8 minutes."
                , R.drawable.smokedhaddockkedgeree));
        recipes.add(new Recipe("Bitterballen", "Melt the butter in a skillet or pan. When melted, add the flour little by little and stir into " +
                "a thick paste. Slowly stir in the stock, making sure the roux absorbs the liquid. Simmer for a couple of minutes on a low heat while you stir " +
                "in the onion, parsley and the shredded meat. The mixture should thicken and turn into a heavy, thick sauce. Pour the mixture into a shallow" +
                " container, cover and refrigerate for several hours, or until the sauce has solidified. Take a heaping tablespoon of the cold, thick sauce " +
                "and quickly roll it into a small ball. Roll lightly through the flour, then the egg and finally the breadcrumbs. Make sure that the egg covers" +
                " the whole surface of the bitterbal. When done, refrigerate the snacks while the oil in your fryer heats up to 190C (375F). Fry four bitter" +
                "ballen at a time, until golden. Serve on a plate with a nice grainy or spicy mustard."
                , R.drawable.bitterballen));
        recipes.add(new Recipe("Pad See Ew", "Mix Sauce in small bowl. Mince garlic into wok with oil. Place over high heat, when hot, add" +
                " chicken and Chinese broccoli stems, cook until chicken is light golden. Push to the side of the wok, crack egg in and scramble. Don't wo" +
                "rry if it sticks to the bottom of the wok - it will char and which adds authentic flavour. Add noodles, Chinese broccoli leaves and sauce" +
                ". Gently mix together until the noodles are stained dark and leaves are wilted. Serve immediately!"
                , R.drawable.padseeew));
        recipes.add(new Recipe("Bakewell tart", "To make the pastry, measure the flour into a bowl and rub in the butter with your finge" +
                "rtips until the mixture resembles fine breadcrumbs. Add the water, mixing to form a soft dough. Roll out the dough on a lightly floured" +
                " work surface and use to line a 20cm/8in flan tin. Leave in the fridge to chill for 30 minutes. Preheat the oven to 200C/400F/Gas 6 (18" +
                "0C fan). Line the pastry case with foil and fill with baking beans. Bake blind for about 15 minutes, then remove the beans and foil and" +
                " cook for a further five minutes to dry out the base. For the filing, spread the base of the flan generously with raspberry jam. Melt th" +
                "e butter in a pan, take off the heat and then stir in the sugar. Add ground almonds, egg and almond extract. Pour into the flan tin and" +
                " sprinkle over the flaked almonds. Bake for about 35 minutes. If the almonds seem to be browning too quickly, cover the tart loosely wit" +
                "h foil to prevent them burning."
                , R.drawable.bakewelltart));
        recipes.add(new Recipe("Tourtiere", "Heat oven to 200C/180C fan/gas 6. Boil the potato until tender, drain and mash, then leave to cool" +
                ". Heat the oil in a non-stick pan, add the mince and onion and quickly fry until browned. Add the garlic, spices, stock, plenty of peppe" +
                "r and a little salt and mix well. Remove from the heat, stir into the potato and leave to cool. Roll out half the pastry and line the b" +
                "ase of a 20-23cm pie plate or flan tin. Fill with the pork mixture and brush the edges of the pastry with water. Roll out the remaining" +
                " dough and cover the pie. Press the edges of the pastry to seal, trimming off the excess. Prick the top of the pastry case to allow stea" +
                "m to escape and glaze the top with the beaten egg. Bake for 30 mins until the pastry is crisp and golden. Serve cut into wedges with a c" +
                "risp green salad. Leftovers are good cold for lunch the next day, served with a selection of pickles."
                , R.drawable.tourtiere));
        recipes.add(new Recipe("Chocolate Cake", "Simply mix all dry ingredients with wet ingredients and blend altogether. Bake for 45 min on " +
                "180 degrees. Decorate with some melted vegan chocolate "
                , R.drawable.chocolatecake));
        recipes.add(new Recipe("Cashew Ghoriba Biscuits", "Preheat the oven at 180 C / Gas 4. Line a baking tray with greaseproof paper." +
                " In a bowl, mix the cashews and icing sugar. Add the egg yolks and orange blossom water and mix to a smooth homogeneous paste. Take lum" +
                "ps of the cashew paste and shape into small balls. Roll the balls in icing sugar and transfer to the baking tray. Push an almond in the" +
                " centre of each ghribia. Bake until the biscuits are lightly golden, about 20 minutes. Keep an eye on them, they burn quickly."
                , R.drawable.cashewghoribabiscuits));
        recipes.add(new Recipe("Breakfast Potatoes", "Before you do anything, freeze your bacon slices that way when you're ready to prep, " +
                "it'll be so much easier to chop! Wash the potatoes and cut medium dice into square pieces. To prevent any browning, place the already cu" +
                "t potatoes in a bowl filled with water. In the meantime, heat 1-2 tablespoons of oil in a large skillet over medium-high heat. Tilt the" +
                " skillet so the oil spreads evenly. Once the oil is hot, drain the potatoes and add to the skillet. Season with salt, pepper, and Old Ba" +
                "y as needed. Cook for 10 minutes, stirring the potatoes often, until brown. If needed, add a tablespoon more of oil. Chop up the bacon an" +
                "d add to the potatoes. The bacon will start to render and the fat will begin to further cook the potatoes. Toss it up a bit! The bacon wi" +
                "ll take 5-6 minutes to crisp. Once the bacon is cooked, reduce the heat to medium-low, add the minced garlic and toss. Season once more. " +
                "Add dried or fresh parsley. Control heat as needed. Let the garlic cook until fragrant, about one minute. Just before serving, drizzle ov" +
                "er the maple syrup and toss. Let that cook another minute, giving the potatoes a caramelized effect. Serve in a warm bowl with a sunny si" +
                "de up egg!"
                , R.drawable.breakfastpotatoes));
        return recipes;
    }

    public ArrayList<Category> Categories(){
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Lamb"));//1
        categories.add(new Category("Dessert"));//2
        categories.add(new Category("Breakfast"));//3
        categories.add(new Category("Beef"));//4
        categories.add(new Category("Chicken"));//5
        categories.add(new Category("Pork"));//6
        categories.add(new Category("Vegan"));//7
        categories.add(new Category("Pasta"));//8
        return categories;
    }
    public ArrayList<RecipeCategory> RecipesCategories(){
        ArrayList<RecipeCategory> recipesCategories = new ArrayList<>();

        recipesCategories.add(new RecipeCategory(1, 1));

        recipesCategories.add(new RecipeCategory(2, 2));

        recipesCategories.add(new RecipeCategory(3, 3));

        recipesCategories.add(new RecipeCategory(4, 4));
        recipesCategories.add(new RecipeCategory(4, 5));

        recipesCategories.add(new RecipeCategory(5, 1));
        recipesCategories.add(new RecipeCategory(5, 8));

        recipesCategories.add(new RecipeCategory(6, 2));
        recipesCategories.add(new RecipeCategory(6, 3));

        recipesCategories.add(new RecipeCategory(7, 4));
        recipesCategories.add(new RecipeCategory(7, 5));
        recipesCategories.add(new RecipeCategory(7, 6));

        recipesCategories.add(new RecipeCategory(8, 2));
        recipesCategories.add(new RecipeCategory(8, 7));

        recipesCategories.add(new RecipeCategory(9, 2));
        recipesCategories.add(new RecipeCategory(10, 3));

        return  recipesCategories;
    }

    public ArrayList<Ingredient> Ingredients(){
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(new Ingredient("Cumin", "2 tsp", 1));
        ingredients.add(new Ingredient("Rapeseed Oil", "1 tbs", 1));
        ingredients.add(new Ingredient("Red Onions", "2 finely chopped", 1));
        ingredients.add(new Ingredient("Garlic Clove", "6", 1));
        ingredients.add(new Ingredient("Green Chili", "3 finely chopped", 1));
        ingredients.add(new Ingredient("Red Pepper", "1 finely chopped ", 1));
        ingredients.add(new Ingredient("Nutmeg", "1 tsp ", 1));
        ingredients.add(new Ingredient("Coriander", "2 tsp", 1));
        ingredients.add(new Ingredient("Lamb Mince", "1kg", 1));

        ingredients.add(new Ingredient("Corn Flour", "1 tsp", 2));
        ingredients.add(new Ingredient("Oranges", "Juice of 2", 2));
        ingredients.add(new Ingredient("Caster Sugar", "2 tbs", 2));
        ingredients.add(new Ingredient("Peaches", "6", 2));
        ingredients.add(new Ingredient("Blueberries", "250g", 2));
        ingredients.add(new Ingredient("Self-raising Flour", "200g", 2));
        ingredients.add(new Ingredient("Butter", "50g", 2));
        ingredients.add(new Ingredient("Muscovado Sugar", "100g", 2));
        ingredients.add(new Ingredient("Cinnamon", "1 tsp ", 2));
        ingredients.add(new Ingredient("Milk", "6 tblsp", 2));

        ingredients.add(new Ingredient("Butter", "50g", 3));
        ingredients.add(new Ingredient("Onion", "1 chopped", 3));
        ingredients.add(new Ingredient("Cardamom", "3 Pods", 3));
        ingredients.add(new Ingredient("Turmeric", "1/4 tsp", 3));
        ingredients.add(new Ingredient("Cinnamon Stick", "1 small", 3));
        ingredients.add(new Ingredient("Bay Leaf", "Sprigs of fresh", 3));
        ingredients.add(new Ingredient("Basmati Rice", "450g", 3));

        ingredients.add(new Ingredient("Butter", "100g", 4));
        ingredients.add(new Ingredient("Flour", "150g", 4));
        ingredients.add(new Ingredient("Beef Stock", "700ml", 4));
        ingredients.add(new Ingredient("Onion", "30g", 4));
        ingredients.add(new Ingredient("Parsley", "1 tbs", 4));
        ingredients.add(new Ingredient("Beef", "400g", 4));
        ingredients.add(new Ingredient("Salt", "Pinch", 4));
        ingredients.add(new Ingredient("Pepper", "Pinch", 4));
        ingredients.add(new Ingredient("Nutmeg", "Pinch", 4));
        ingredients.add(new Ingredient("Flour", "50g", 4));
        ingredients.add(new Ingredient("Eggs", "2 Beaten ", 4));
        ingredients.add(new Ingredient("Breadcrumbs", "50g", 4));

        ingredients.add(new Ingredient("rice stick noodles", "6oz/180g", 5));
        ingredients.add(new Ingredient("dark soy sauce", "2 tbsp", 5));
        ingredients.add(new Ingredient("oyster sauc", "2 tbsp", 5));
        ingredients.add(new Ingredient("soy sauce", "2 tsp", 5));
        ingredients.add(new Ingredient("white vinegar", "2 tsp", 5));
        ingredients.add(new Ingredient("sugar", "2 tsp", 5));
        ingredients.add(new Ingredient("water", "2 tbsp", 5));
        ingredients.add(new Ingredient("peanut oil", "2 tbsp", 5));
        ingredients.add(new Ingredient("garlic", "2 cloves", 5));
        ingredients.add(new Ingredient("Chicken", "1 cup", 5));
        ingredients.add(new Ingredient("Egg", "1", 5));
        ingredients.add(new Ingredient("Chinese broccoli", "4 cups", 5));


        ingredients.add(new Ingredient("plain flour", "175g/6oz", 6));
        ingredients.add(new Ingredient("chilled butter", "75g/2½oz", 6));
        ingredients.add(new Ingredient("cold water", "2-3 tbsp", 6));
        ingredients.add(new Ingredient("raspberry jam", "1 tbsp", 6));
        ingredients.add(new Ingredient("butter", "125g/4½oz", 6));
        ingredients.add(new Ingredient("caster sugar", "125g/4½oz", 6));
        ingredients.add(new Ingredient("ground almonds", "125g/4½oz", 6));
        ingredients.add(new Ingredient("free-range egg, beaten", "1", 6));
        ingredients.add(new Ingredient("almond extract", "½ tsp", 6));
        ingredients.add(new Ingredient("flaked almonds", "50g/1¾oz", 6));

        ingredients.add(new Ingredient("Potatoes", "1 medium", 7));
        ingredients.add(new Ingredient("Sunflower Oil", "1 tsp", 7));
        ingredients.add(new Ingredient("Minced Pork", "500g", 7));
        ingredients.add(new Ingredient("Onion", "1 finely chopped", 7));
        ingredients.add(new Ingredient("Garlic Clove", "1 finely chopped", 7));
        ingredients.add(new Ingredient("Cinnamon", "¼ tsp", 7));
        ingredients.add(new Ingredient("Allspice", "¼ tsp", 7));
        ingredients.add(new Ingredient("Nutmeg", "¼ tsp", 7));
        ingredients.add(new Ingredient("Vegetable Stock", "100ml", 7));
        ingredients.add(new Ingredient("Shortcrust Pastry", "400g", 7));
        ingredients.add(new Ingredient("Egg", "To Glaze", 7));


        ingredients.add(new Ingredient("self raising flour", "1 1/4 cup", 8));
        ingredients.add(new Ingredient("coco sugar", "1/2 cup", 8));
        ingredients.add(new Ingredient("cacao", "1/3 cup raw", 8));
        ingredients.add(new Ingredient("baking powder", "1 tsp", 8));
        ingredients.add(new Ingredient("flax eggs", "2", 8));
        ingredients.add(new Ingredient("almond milk", "1/2 cup", 8));
        ingredients.add(new Ingredient("vanilla", "1 tsp", 8));
        ingredients.add(new Ingredient("water", "1/2 cup boiling", 8));

        ingredients.add(new Ingredient("Cashew Nuts", "250g", 9));
        ingredients.add(new Ingredient("Icing Sugar", "100g", 9));
        ingredients.add(new Ingredient("Egg Yolks", "2", 9));
        ingredients.add(new Ingredient("Orange Blossom Water", "2 tbs", 9));
        ingredients.add(new Ingredient("Icing Sugar", "To Glaze", 9));
        ingredients.add(new Ingredient("Almonds", "100g", 9));

        ingredients.add(new Ingredient("Potatoes", "3 Medium", 10));
        ingredients.add(new Ingredient("Olive Oil", "1 tbs", 10));
        ingredients.add(new Ingredient("Bacon", "2 strips", 10));
        ingredients.add(new Ingredient("Garlic Clove", "Minced", 10));
        ingredients.add(new Ingredient("Maple Syrup", "1 tbs", 10));
        ingredients.add(new Ingredient("Parsley", "Garnish", 10));
        ingredients.add(new Ingredient("Salt", "Pinch", 10));
        ingredients.add(new Ingredient("Pepper", "Pinch", 10));
        ingredients.add(new Ingredient("Allspice", "To taste", 10));


        return ingredients;
    }


}
