Feature: Vérifier qu'un compte rendu de TP contienne le bon numéro de TP de la semaine

Scenario: Le numéro de TP de la semaine, devrait être mentionné dans le compte rendu
    Given un numéro de TP de la semaine
    And un compte rendu de TP
    Then le numéro de TP devrait se trouvé dans le compte rendu
