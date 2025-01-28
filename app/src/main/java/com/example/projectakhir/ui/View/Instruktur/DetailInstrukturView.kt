import androidx.compose.ui.graphics.Color

// Define a cute pink color palette
val PinkSoft = Color(0xFFF8A1D0) // Light Pink
val PinkDark = Color(0xFFEE76A1) // Darker Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailInstrukturView(
    id_instruktur: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (Int) -> Unit = {},
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailInstruktur.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = { viewModel.getDetailInstruktur() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(id_instruktur) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp),
                containerColor = PinkSoft // Set FloatingActionButton color
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mahasiswa",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailIntrukturUiState.collectAsState()

        BodyDetailInstruktur(
            modifier = Modifier.padding(innerPadding),
            detailInstruUiState = detailUiState,
            retryAction = { viewModel.getDetailInstruktur() }
        )
    }
}

@Composable
fun BodyDetailInstruktur(
    modifier: Modifier = Modifier,
    detailInstruUiState: DetailInstruUiState,
    retryAction: () -> Unit = {}
) {
    when (detailInstruUiState) {
        is DetailInstruUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailInstruUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetail(instruktur = detailInstruUiState.instruktur)
            }
        }
        is DetailInstruUiState.Error -> {
            OnError(
                retryAction = retryAction,
                modifier = modifier.fillMaxSize()
            )
        }
        else -> {
            Text("Unexpected state encountered")
        }
    }
}

@Composable
fun ItemDetail(
    instruktur: Instruktur
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PinkSoft, // Set pink background color for the card
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetail(judul = "Nama Instruktur", isinya = instruktur.nama_instruktur)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "ID Instruktur", isinya = instruktur.id_instruktur.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Email", isinya = instruktur.email)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Deskripsi", isinya = instruktur.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Nomor Telepon", isinya = instruktur.nomor_telepon)
        }
    }
}

@Composable
fun ComponentDetail(
    judul: String,
    isinya: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PinkDark // Set pink color for the title text
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}
