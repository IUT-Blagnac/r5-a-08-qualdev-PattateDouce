from behave import *
import os


@given('un numéro de TP de la semaine')
def step_impl(context):
    context.number = "07"


@given('un compte rendu de TP')
def step_impl(context):
    context.practical_work = "TP07 - Prog++ - Rémy Guibert G2.pdf"


@then('le numéro de TP devrait se trouvé dans le compte rendu')
def step_impl(context):
    print("Recherche de TP" + str(context.number) + " dans le compte rendu : " + context.practical_work)
    assert os.system("pdfgrep TP" + str(context.number) + " \"" + context.practical_work + "\"") == 0
