<?php

// mail
function sprawdz_mail($email)
{
    $czesci = explode("@", $email);

    if (count($czesci) == 2) {
        return $czesci[1];
    } else {
        return "Niepoprawny adres e-mail";
    }
}

// zmienne
$imie = "";
$email = "";
$wiadomosc = "";

$ostrzezenie = "";
$domena = "";
$raport = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $imie = htmlspecialchars($_POST["imie"]);
    $email = htmlspecialchars($_POST["email"]);
    $wiadomosc = htmlspecialchars($_POST["wiadomosc"]);

    // domena
    $domena = sprawd_mail($email);

    // moderacja
    if (preg_match('/\b(dupa|dupek)\b/i', $wiadomosc)) {
        $ostrzezenie = "UWAGA! Wiadomość zawiera niedozwolone słowa.";
    }

    // tablica
    $dane = [
        "Imię: " . $imie,
        "Email: " . $email,
        "Wiadomość: " . $wiadomosc
    ];

    // sklejanie
    $raport = implode(" | ", $dane);
}

?>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Przetwarzanie danych w PHP</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;

            display: flex;
            justify-content: center;
            align-items: center;

            height: 100vh;
            margin: 0;
        }

        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            width: 400px;

        }

        h2 {
            text-align: center;
        }

        input,
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 15px;

            border: 1px solid #ccc;
            border-radius: 5px;

            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;

            background-color: #4CAF50;
            color: white;

            border: none;
            border-radius: 5px;

            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .wynik {
            margin-top: 20px;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }

        .ostrzezenie {
            color: red;
            font-weight: bold;
        }

    </style>
</head>
<body>

<div class="container">

    <h2>Formularz kontaktowy</h2>

    <form method="POST">

        <input type="text" name="imie" placeholder="Podaj imię" required>

        <input type="email" name="email" placeholder="Podaj e-mail" required>

        <textarea name="wiadomosc" rows="5" placeholder="Wpisz wiadomość" required></textarea>

        <button type="submit">Wyślij</button>

    </form>

    <?php if ($_SERVER["REQUEST_METHOD"] == "POST"): ?>

        <div class="wynik">

            <p><strong>Domena e-mail:</strong> <?php echo $domena; ?></p>

            <?php if ($ostrzezenie != ""): ?>
                <p class="ostrzezenie">
                    <?php echo $ostrzezenie; ?>
                </p>
            <?php endif; ?>

            <p><strong>Raport:</strong></p>

            <p><?php echo $raport; ?></p>

        </div>

    <?php endif; ?>

</div>

</body>
</html>