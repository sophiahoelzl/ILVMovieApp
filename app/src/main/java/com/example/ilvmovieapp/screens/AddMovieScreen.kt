package com.example.ilvmovieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ilvmovieproject.R
import androidx.navigation.NavController
import com.example.ilvmovieapp.TopAppBar
import com.example.ilvmovieapp.ViewModel.MovieViewModel
import com.example.ilvmovieapp.models.Genre

/*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ilvmovieapp.ViewModel.MovieViewModel
import com.example.ilvmovieapp.models.Genre
import com.example.ilvmovieapp.models.ListItemSelectable
import com.example.ilvmovieproject.R
import com.example.ilvmovieproject.models.Movie

@Composable
fun AddMovie(navController: NavController, moviesViewModel: MovieViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            com.example.ilvmovieapp.TopAppBar(navController = navController, title = "ADD A MOVIE")

        },
    ) { padding ->
        MainContent(navController,
            Modifier.padding(padding),
            addMovie = { m -> moviesViewModel.addMovie(m)})
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    navController: NavController, modifier: Modifier = Modifier, addMovie: (Movie) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by remember {
                mutableStateOf("")
            }

            var year by remember {
                mutableStateOf("")
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(genres.map { genre ->
                    ListItemSelectable(
                        title = genre.toString(), isSelected = false
                    )
                })
            }

            var director by remember {
                mutableStateOf("")
            }

            var actors by remember {
                mutableStateOf("")
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(false)
            }

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = title.isEmpty(),
                trailingIcon = {
                    if (title.isEmpty())
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (title.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { year = it },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = year.isEmpty(),
                trailingIcon = {
                    if (year.isEmpty())
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (year.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp), rows = GridCells.Fixed(3)
            ) {
                items(genreItems) { genreItem ->
                    Chip(modifier = Modifier.padding(2.dp), colors = ChipDefaults.chipColors(
                        backgroundColor = if (genreItem.isSelected) colorResource(id = R.color.purple_200)
                        else colorResource(id = R.color.white)
                    ), onClick = {
                        genreItems = genreItems.map {
                            if (it.title == genreItem.title) {
                                genreItem.copy(isSelected = !genreItem.isSelected)
                            } else {
                                it
                            }
                        }
                    }) {
                        Text(text = genreItem.title)
                    }
                }
            }
            if (!genreItems.any { it.isSelected }) {
                Text(
                    text = "at least one genre must be selected",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { director = it },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = director.isEmpty(),
                trailingIcon = {
                    if (director.isEmpty())
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (director.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { actors = it },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = actors.isEmpty(),
                trailingIcon = {
                    if (actors.isEmpty())
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (actors.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it },
                label = {
                    Text(
                        textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)
                    )
                },
                isError = false
            )

            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    rating = if (it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = !rating.matches(("\\d.?\\d?\\d?").toRegex()),
                trailingIcon = {
                    if (rating.isEmpty())
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (!rating.matches(("\\d.?\\d?\\d?").toRegex())) {
                Text(
                    text = "field must be a number with a maximum of decimal places",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            Button(enabled = isEnabledSaveButton, onClick = {
                var m = Movie(
                    id = title.replace(" ", ""),
                    title = title,
                    year = year,
                    genre = genres.filter {genre ->
                        genreItems.filter { selected -> selected.isSelected }
                            .map { string -> string.title }.contains(genre.name)
                    },
                    director = director,
                    actors = actors,
                    plot = plot,
                    images = listOf(
                        "https://thumbs.dreamstime.com/b/happy-cat-closeup-portrait-funny-smile-cardboard-young-blue-background-102078702.jpg"
                    ),
                    rating = rating.replace(",",".").toFloat()
                )

                addMovie(m)
                navController.navigate(route = Screens.Home.route)
            }) {
                Text(text = stringResource(R.string.add))
            }

            isEnabledSaveButton =
                (title.isNotEmpty()
                        && year.isNotEmpty()
                        && genreItems.any { it.isSelected }
                        && director.isNotEmpty()
                        && actors.isNotEmpty()
                        && rating.matches(("\\d.?\\d?\\d?").toRegex()
                ))
        }
    }
}
*/


@Composable
fun AddMovie(navController: NavController, moviesViewModel: MovieViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(navController = navController, title = "ADD MOVIE")
        },
    ) { padding ->
        MainContent(Modifier.padding(padding), moviesViewModel = moviesViewModel, navController)
    }
    moviesViewModel.validation()
}

@Composable
fun InputField(
    text: MutableState<String>,
    errorState: MutableState<Boolean>,
    label: Int,
    validateMethod: () -> Unit
) {
    OutlinedTextField(
        value = text.value,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            text.value = it
            validateMethod()
        },
        label = { Text(stringResource(id = label)) },
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, moviesViewModel: MovieViewModel, navController: NavController) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                text = moviesViewModel.title,
                label = R.string.enter_movie_title,
                errorState = moviesViewModel.titleError,
                validateMethod = {moviesViewModel.validateTitle()}
            )
            PrintErrorMsg(value = moviesViewModel.titleError.value, text = stringResource(R.string.invalid_input))

            InputField(
                text = moviesViewModel.year,
                label = R.string.enter_movie_year,
                errorState = moviesViewModel.yearError,
                validateMethod = {moviesViewModel.validateYear()}
            )
            PrintErrorMsg(value = moviesViewModel.yearError.value, text = stringResource(R.string.invalid_input))

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            PrintErrorMsg(value = moviesViewModel.genreError.value, text = stringResource(R.string.choose_genre))

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)
            ) {
                items(moviesViewModel.genreItems.value) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            moviesViewModel.genreItems.value =
                                moviesViewModel.genreItems.value.map {
                                    if (it.title == genreItem.title) {
                                        genreItem.copy(isSelected = !genreItem.isSelected)
                                    } else {
                                        it
                                    }
                                }
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            InputField(
                text = moviesViewModel.director,
                label = R.string.enter_director,
                errorState = moviesViewModel.directorError,
                validateMethod = {moviesViewModel.validateDirector()}
            )
            PrintErrorMsg(value = moviesViewModel.directorError.value, text = stringResource(R.string.invalid_input))


            InputField(
                text = moviesViewModel.actors,
                label = R.string.enter_actors,
                errorState = moviesViewModel.actorsError,
                validateMethod = {moviesViewModel.validateActors()}
            )
            PrintErrorMsg(value = moviesViewModel.actorsError.value, text = stringResource(R.string.invalid_input))


            InputField(
                text = moviesViewModel.plot,
                label = R.string.enter_plot,
                errorState = moviesViewModel.plotError,
                validateMethod = {moviesViewModel.validatePlot()}
            )
            PrintErrorMsg(value = moviesViewModel.plotError.value, text = stringResource(R.string.invalid_input))


            InputField(
                text = moviesViewModel.rating,
                label = R.string.enter_rating,
                errorState = moviesViewModel.ratingError,
                validateMethod = {moviesViewModel.validateRating()}
            )
            PrintErrorMsg(value = moviesViewModel.yearError.value, text = stringResource(R.string.decimal_number))


            Button(
                enabled = moviesViewModel.addButtonEnabled.value,
                onClick = {
                    val genreList: MutableList<Genre> = mutableListOf()
                    moviesViewModel.genreItems.value.filter { it.isSelected }
                        .forEach { genreList.add(Genre.valueOf(it.title)) }


                    moviesViewModel.addMovie(
                        moviesViewModel.title.value,
                        moviesViewModel.year.value,
                        genreList,
                        moviesViewModel.director.value,
                        moviesViewModel.actors.value,
                        moviesViewModel.plot.value,
                        moviesViewModel.rating.value
                    )

                    navController.navigate(route = Screens.Home.route)
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }

}

@Composable
fun PrintErrorMsg(value: Boolean, text: String) {
    if (value) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Red
        )
    }
}